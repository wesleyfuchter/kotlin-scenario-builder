package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import java.time.LocalDate

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
    val orderDate: LocalDate,
    val products: ArrayList<OrderProduct> = ArrayList()
) {

    fun orderProduct(productName: String, amount: Int)
            = products.add(OrderProduct(productName = productName, amount = amount))

}