package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

data class Scenario (

        val cities: List<City>,
        val categories: List<ProductCategory>,
        val customers: List<Customer>,
        val products: List<Product>,
        val orders: List<Order>

) {
    class Builder {

        private val _cities = ArrayList<City>()
        private val _categories = ArrayList<ProductCategory>()
        private val _customers = ArrayList<Customer>()
        private val _products = ArrayList<Product>()
        private val _orders = ArrayList<Order>()

        fun city(name: String)
                = _cities.add(City(name = name))

        fun customer(name: String, city: String)
                = _customers.add(Customer(name = name, city = city))

        fun productCategory(name: String)
                = _categories.add(ProductCategory(name = name))

        fun product(name: String, category: String)
                = _products.add(Product(name = name, category = category))

        fun order(customer: String,
                  products: List<OrderProduct> = ArrayList(),
                  finished: Boolean = false,
                  buildScenario: Order.Builder.() -> Unit = {})
                = _orders.add(Order(customer = customer, finished = finished, products = products))

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