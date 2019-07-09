package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

data class City (
        val name: String
)

data class Customer (
        val name: String,
        val city: String
)

data class ProductCategory (
        val name: String
)

data class Product (
        val name: String,
        val category: String
)

data class OrderProduct (
        val productName: String,
        val amount: Int
)

data class Order (
    val customer: String,
    val finished: Boolean? = false,
    val products: List<OrderProduct>
) {
    class Builder {

        private val _products = ArrayList<OrderProduct>()

        fun orderProduct(productName: String, amount: Int)
                = _products.add(OrderProduct(productName = productName, amount = amount))

        fun build(customer: String, finished: Boolean?) = Order(customer, finished, _products)

    }
}