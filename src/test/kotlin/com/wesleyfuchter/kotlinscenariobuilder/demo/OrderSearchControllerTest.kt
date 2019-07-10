package com.wesleyfuchter.kotlinscenariobuilder.demo

import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.Scenario
import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.ScenarioLoader
import org.junit.Test
import java.time.LocalDate
import java.time.Month

class OrderSearchControllerTest {

    //@Autowired <- enable with spring context implemented
    private val loader: ScenarioLoader = ScenarioLoader()

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

        order(customer = "Jack Shepard",
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

    }

    @Test fun `test should return all customers with active orders for a given delivery city`() {
        loader(scenario) {

            println("test body start")

        }
    }

    @Test fun `test should return all customers with inactive orders for a given delivery city`() {

    }

    @Test fun `test should return all customers with active orders for a given product category`() {

    }

    @Test fun `test should return all customers with inactive orders for a given product category`() {

    }

    @Test fun `test should return all customers with active orders for a given day`() {

    }

    @Test fun `test should return all customers with inactive orders for a given day`() {

    }

}