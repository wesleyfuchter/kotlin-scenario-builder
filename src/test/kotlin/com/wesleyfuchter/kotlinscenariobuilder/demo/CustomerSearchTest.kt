package com.wesleyfuchter.kotlinscenariobuilder.demo

import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.DatabaseSchema
import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.DatabaseScenario
import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.ScenarioLoader
import com.wesleyfuchter.kotlinscenariobuilder.demo.search.OrderSearchResponse
import com.wesleyfuchter.kotlinscenariobuilder.demo.model.CustomerSearchService
import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.iterable.Extractor
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.Month

@RunWith(SpringRunner::class)
@SpringBootTest
@EnableAutoConfiguration
@Transactional
@Rollback
class CustomerSearchTest {

    private val scenario = DatabaseScenario.with {

        city(name = "Los Angeles")
        city(name = "Chicago")
        city(name = "Boston")

        customer(name = "Jack Shepherd", city = "Los Angeles")
        customer(name = "James Ford", city = "Los Angeles")
        customer(name = "Hugo Reyes", city = "Boston")
        customer(name = "John Lock", city = "Boston")
        customer(name = "Kate Austin", city = "Los Angeles")
        customer(name = "Penny Wildmore", city = "Chicago")

        productCategory(name = "Car")
        productCategory(name = "Home")
        productCategory(name = "Sports")

        product(name = "Chevrolet Camaro", category = "Car")
        product(name = "Land Hover", category = "Car")
        product(name = "Ferrari", category = "Car")

        product(name = "TV", category = "Home")
        product(name = "Book Shelf", category = "Home")

        product(name = "Baseball", category = "Sports")
        product(name = "Basket", category = "Sports")
        product(name = "Soccer Ball", category = "Sports")

        order(customer = "Jack Shepherd",
                orderDate = LocalDate.of(2019, Month.JUNE, 14), finished = false) {
            orderProduct(productName = "Ferrari", amount = 1)
            orderProduct(productName = "Soccer Ball", amount = 2)
        }
        order(customer = "Jack Shepherd",
                orderDate = LocalDate.of(2019, Month.FEBRUARY, 3), finished = true) {
            orderProduct(productName = "Baseball", amount = 50)
        }
        order(customer = "James Ford",
                orderDate = LocalDate.of(2019, Month.JANUARY, 3), finished = true) {
            orderProduct(productName = "Chevrolet Camaro", amount = 2)
        }
        order(customer = "James Ford",
                orderDate = LocalDate.of(2019, Month.MAY, 8), finished = false) {
            orderProduct(productName = "TV", amount = 2)
            orderProduct(productName = "Book Shelf", amount = 3)
        }
        order(customer = "James Ford",
                orderDate = LocalDate.of(2019, Month.JULY, 4), finished = false) {
            orderProduct(productName = "Soccer Ball", amount = 5)
        }
        order(customer = "Hugo Reyes",
                orderDate = LocalDate.of(2019, Month.FEBRUARY, 14), finished = true) {
            orderProduct(productName = "Land Hover", amount = 2)
        }
        order(customer = "Hugo Reyes",
                orderDate = LocalDate.of(2019, Month.JUNE, 30), finished = false) {
            orderProduct(productName = "Ferrari", amount = 1)
            orderProduct(productName = "TV", amount = 3)
        }
        order(customer = "John Lock",
                orderDate = LocalDate.of(2019, Month.JUNE, 14), finished = false) {
            orderProduct(productName = "TV", amount = 1)
        }
        order(customer = "John Lock",
                orderDate = LocalDate.of(2019, Month.JULY, 10), finished = true) {
            orderProduct(productName = "Land Hover", amount = 2)
            orderProduct(productName = "Soccer Ball", amount = 1)
        }
        order(customer = "Kate Austin",
                orderDate = LocalDate.of(2019, Month.MAY, 22), finished = false) {
            orderProduct(productName = "Chevrolet Camaro", amount = 2)
        }
        order(customer = "Kate Austin",
                orderDate = LocalDate.of(2019, Month.JANUARY, 13), finished = true) {
            orderProduct(productName = "Ferrari", amount = 1)
        }

    }

    @Test fun `test should return all customers with active orders for a given delivery city`() {
        loader(scenario, schema) {
            assertThat(customerSearchService.searchByRequest(
                    CustomerSearchRequest(
                            cityToDelivery = cities.findFirstByName("Los Angeles"),
                            finished = false
                    )
            )).`as`("Jack Shepherd, Kate Austin and James Ford has active orders to deliver in Los Angeles")
                    .extracting(customerTitle)
                    .containsExactlyInAnyOrder("Order to Jack Shepherd at 14/06/2019",
                            "Order to James Ford at 08/05/2019",
                            "Order to James Ford at 04/07/2019",
                            "Order to Kate Austin at 22/05/2019")
        }
    }

    @Test fun `test should return all customers with inactive orders for a given delivery city`() {
        loader(scenario, schema) {
            assertThat(customerSearchService.searchByRequest(
                    CustomerSearchRequest(
                            cityToDelivery = cities.findFirstByName("Boston"),
                            finished = true
                    )
            )).`as`("John Lock and Hugo Reyes has inactive orders to deliver in Boston")
                    .extracting(customerTitle)
                    .containsExactlyInAnyOrder("Order to Hugo Reyes at 14/02/2019", "Order to John Lock at 10/07/2019")
        }
    }

    @Test fun `test should return all customers with active orders for a given product category`() {
        loader(scenario, schema) {
            assertThat(customerSearchService.searchByRequest(
                    CustomerSearchRequest(
                            productCategory = productCategories.findFirstByName("Sports"),
                            finished = false
                    )
            )).`as`("Jack Shepherd and James Ford has active orders to Sports products")
                    .extracting(customerTitle)
                    .containsExactlyInAnyOrder("Order to Jack Shepherd at 14/06/2019", "Order to James Ford at 04/07/2019")
        }
    }

    @Test fun `test should return all customers with inactive orders for a given product category`() {
        loader(scenario, schema) {
            assertThat(customerSearchService.searchByRequest(
                    CustomerSearchRequest(
                            productCategory = productCategories.findFirstByName("Car"),
                            finished = true
                    )
            )).`as`("James Ford, Hugo Reyes, John Lock and Kate Austin has inactive orders to cars products")
                    .extracting(customerTitle)
                    .containsExactlyInAnyOrder("Order to James Ford at 03/01/2019",
                            "Order to Hugo Reyes at 14/02/2019",
                            "Order to John Lock at 10/07/2019",
                            "Order to Kate Austin at 13/01/2019")
        }
    }

    @Test fun `test should return all customers with inactive orders for a given range of days`() {
        loader(scenario, schema) {
            orders.findAll()
            assertThat(customerSearchService.searchByRequest(
                    CustomerSearchRequest(
                            startDate = LocalDate.of(2019, Month.JANUARY, 1),
                            endDate = LocalDate.of(2019, Month.APRIL, 30),
                            finished = true
                    )
            )).`as`("Jack Shepherd, James Ford, Hugo Reyes, and Kate Austin has inactive orders for the first quarter of year")
                    .extracting(customerTitle)
                    .containsExactlyInAnyOrder("Order to Jack Shepherd at 03/02/2019",
                            "Order to James Ford at 03/01/2019",
                            "Order to Hugo Reyes at 14/02/2019",
                            "Order to Kate Austin at 13/01/2019")
        }
    }

    private val customerTitle = value(OrderSearchResponse::title)
    private fun <T, R> value(extractor: (T) -> R) = Extractor<T, R> { extractor(it) }

    @Autowired private lateinit var loader: ScenarioLoader
    @Autowired private lateinit var schema: DatabaseSchema
    @Autowired private lateinit var customerSearchService: CustomerSearchService

}