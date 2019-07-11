package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.*
import org.springframework.stereotype.Component

@Component
class ScenarioLoader {

    operator fun invoke(scenario: DatabaseScenario, schema: DatabaseSchema, runTest: DatabaseScenarioRunner.() -> Unit) {
        load(scenario, schema)
        DatabaseScenarioRunner(scenario, schema).runTest()
    }

    fun load(scenario: DatabaseScenario, schema: DatabaseSchema) {
        orders(schema, scenario,
                customers(schema, scenario,
                        cities(schema, scenario)),
                products(schema, scenario,
                        categories(schema, scenario)))
    }

    private fun orders(schema: DatabaseSchema, scenario: DatabaseScenario, customers: MutableIterable<Customer>, products: MutableIterable<Product>)
            = schema.orders.saveAll(scenario.orders.map { order ->
        Order(
                customer = customers.find { customer -> customer.name == order.customer }!!,
                finished = order.finished,
                orderDate = order.orderDate,
                products = order.products.map { orderProduct ->
                    OrderProduct(
                            product = products.find { product -> product.name == orderProduct.productName }!!,
                            amount = orderProduct.amount
                    )
                })
    })

    private fun products(schema: DatabaseSchema, scenario: DatabaseScenario, categories: MutableIterable<ProductCategory>): MutableIterable<Product>
            = schema.products.saveAll(scenario.products.map { product ->
        Product(
                name = product.name,
                category = categories.find { category -> category.name == product.category }!!
        )
    })

    private fun categories(schema: DatabaseSchema, scenario: DatabaseScenario): MutableIterable<ProductCategory>
            = schema.productCategories.saveAll(scenario.categories.map { category -> ProductCategory(name = category.name) })

    private fun customers(schema: DatabaseSchema, scenario: DatabaseScenario, cities: MutableIterable<City>): MutableIterable<Customer>
            = schema.customers.saveAll(scenario.customers.map { customer ->
        Customer(
                name = customer.name,
                city = cities.find { city -> city.name == customer.city }!!
        )
    })

    private fun cities(schema: DatabaseSchema, scenario: DatabaseScenario): MutableIterable<City>
            = schema.cities.saveAll(scenario.cities.map { city -> City(name = city.name) })

    class DatabaseScenarioRunner(val scenario: DatabaseScenario,
                                 private val schema: DatabaseSchema) : DatabaseSchema by schema


}
