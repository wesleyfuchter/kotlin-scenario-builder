package com.wesleyfuchter.kotlinscenariobuilder.demo

import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.Customer
import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.DatabaseSchema
import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.Scenario
import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.ScenarioLoader
import com.wesleyfuchter.kotlinscenariobuilder.demo.model.OrderSearchResponse
import com.wesleyfuchter.kotlinscenariobuilder.demo.model.OrderService
import com.wesleyfuchter.kotlinscenariobuilder.demo.search.SearchRequest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.iterable.Extractor
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.Month

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [DatabaseConfiguration::class])
@EnableAutoConfiguration
class OrderSearchServiceTest {

    @Autowired private lateinit var loader: ScenarioLoader
    @Autowired private lateinit var schema: DatabaseSchema
    @Autowired private lateinit var orderService: OrderService

    private val scenario = Scenario.with {

        city(name = "New York")
        city(name = "Los Angeles")
        city(name = "Chicago")
        city(name = "Boston")

        customer(name = "Jack Shepard", city = "Los Angeles")
        customer(name = "James Ford", city = "New York")
        customer(name = "Hugo Reyes", city = "Boston")
        customer(name = "John Lock", city = "Chicago")
        customer(name = "Kate Austin", city = "Los Angeles")

        productCategory(name = "Car")
        productCategory(name = "Home")
        productCategory(name = "Sports")

        product(name = "Chevrolet Camaro", category = "Car")
        product(name = "Land Hover", category = "Car")
        product(name = "Ferrari", category = "Car")

        product(name = "Couch", category = "Home")
        product(name = "TV", category = "Home")
        product(name = "Book Shelf", category = "Home")

        product(name = "Baseball", category = "Sports")
        product(name = "Basket", category = "Sports")
        product(name = "Soccer Ball", category = "Sports")

        order(customer = "Jack Shepherd",
                orderDate = LocalDate.of(2019, Month.JULY, 4)) {
            orderProduct(productName = "Basket", amount = 3)
            orderProduct(productName = "TV", amount = 4)
            orderProduct(productName = "Chevrolet Camaro", amount = 2)
        }

        order(customer = "Jack Shepard")
        order(customer = "James Ford")
        order(customer = "James Ford")
        order(customer = "James Ford")
        order(customer = "Hugo Reyes")
        order(customer = "Hugo Reyes", finished = true)
        order(customer = "John Lock", finished = true)
        order(customer = "Kate Austin", finished = true)
        order(customer = "James Ford", finished = true)

    }

    @Test fun `test should return all customers with active orders for a given delivery city`() {
        loader(scenario, schema) {
            assertThat(orderService.searchByRequest(
                    SearchRequest(
                            cityToDelivery = "Los Angeles",
                            finished = false
                    )
            )).`as`("Jack Shepherd, Hugo Reyes and James Ford has active orders to deliver in Los Angeles")
                    .extracting(customerNameExtractor)
                    .containsExactlyInAnyOrder("Jack Shepherd", "Hugo Reyes", "James Ford")
        }
    }

    @Test fun `test should return all customers with inactive orders for a given delivery city`() {
        loader(scenario, schema) {
            assertThat(orderService.searchByRequest(
                    SearchRequest(
                            cityToDelivery = "Boston",
                            finished = true
                    )
            )).`as`("John Lock and Kate Austin has inactive orders to deliver in Boston")
                    .extracting(customerNameExtractor)
                    .containsExactlyInAnyOrder("John Lock", "Kate Austin")
        }
    }

    @Test fun `test should return all customers with active orders for a given product category`() {
        loader(scenario, schema) {
            assertThat(orderService.searchByRequest(
                    SearchRequest(
                            productCategory = "Sports",
                            finished = false
                    )
            )).`as`("Just James Ford has active orders to Sports products")
                    .extracting(customerNameExtractor)
                    .containsExactlyInAnyOrder("James Ford")
        }
    }

    @Test fun `test should return all customers with inactive orders for a given product category`() {
        loader(scenario, schema) {
            assertThat(orderService.searchByRequest(
                    SearchRequest(
                            productCategory = "Sports",
                            finished = true
                    )
            )).`as`("Kate Austin, John Lock, and Jack Shepherd has active orders to Sports products")
                    .extracting(customerNameExtractor)
                    .containsExactlyInAnyOrder("Kate Austin", "John Lock", "Jack Shepherd")
        }
    }

    @Test fun `test should return all customers with inactive orders for a given range of days`() {
        loader(scenario, schema) {
            assertThat(orderService.searchByRequest(
                    SearchRequest(
                            startDate = LocalDate.of(2019, Month.JANUARY, 1),
                            endDate = LocalDate.of(2019, Month.APRIL, 30),
                            finished = true
                    )
            )).`as`("Kate Austin, John Lock, and Jack Shepherd has active orders for the first quarter of year")
                    .extracting(customerNameExtractor)
                    .containsExactlyInAnyOrder("Hugo Reyes", "James Ford")
        }
    }

    private val customerNameExtractor = value(OrderSearchResponse::orderCustomerName)

    private fun <T, R> value(extractor: (T) -> R) = Extractor<T, R> { extractor(it) }

}