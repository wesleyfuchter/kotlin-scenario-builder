package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import java.time.LocalDate

data class Scenario (

        val cities: List<CityTEntity>,
        val categories: List<ProductCategoryTEntity>,
        val customers: List<CustomerTEntity>,
        val products: List<ProductTEntity>,
        val orders: List<OrderTEntity>

) {
    class Builder {

        private val _cities = ArrayList<CityTEntity>()
        private val _categories = ArrayList<ProductCategoryTEntity>()
        private val _customers = ArrayList<CustomerTEntity>()
        private val _products = ArrayList<ProductTEntity>()
        private val _orders = ArrayList<OrderTEntity>()

        fun city(name: String)
                = _cities.add(CityTEntity(name = name))

        fun customer(name: String, city: String)
                = _customers.add(CustomerTEntity(name = name, city = city))

        fun productCategory(name: String)
                = _categories.add(ProductCategoryTEntity(name = name))

        fun product(name: String, category: String)
                = _products.add(ProductTEntity(name = name, category = category))

        fun order(customer: String,
                  finished: Boolean = false,
                  orderDate: LocalDate = LocalDate.now(),
                  buildScenario: OrderTEntity.() -> Unit = {})
                = _orders.add(
                OrderTEntity(customer = customer, finished = finished, orderDate = orderDate).apply(buildScenario))

        fun build() = Scenario(cities = _cities,
                        customers = _customers,
                        categories = _categories,
                        products = _products,
                        orders = _orders)

    }

    companion object {
        fun with(buildScenario: Builder.() -> Unit) = Builder().apply(buildScenario).build()
    }

}