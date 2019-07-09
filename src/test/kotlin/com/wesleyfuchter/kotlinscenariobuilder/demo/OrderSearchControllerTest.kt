package com.wesleyfuchter.kotlinscenariobuilder.demo

import com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest.Scenario

class OrderSearchControllerTest {

    val scenario = Scenario.with {

        city(name = "New York")
        city(name = "Los Angeles")
        city(name = "Chicago")
        city(name = "Boston")

        customer("Jack Shepard", city = "Los Angeles")
        customer("James Ford", city = "New York")
        customer("Hugo Reyes", city = "Boston")
        customer("John Lock", city = "Chicago")
        customer("Kate Austin", city = "Los Angeles")

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

        order(customer = "Jack Shepard") {
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

}