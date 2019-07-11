package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import java.time.LocalDate

data class CityTEntity (
        val name: String
)

data class CustomerTEntity (
        val name: String,
        val city: String
)

data class ProductCategoryTEntity (
        val name: String
)

data class ProductTEntity (
        val name: String,
        val category: String
)

data class OrderProductTEntity (
        val productName: String,
        val amount: Int
)

data class OrderTEntity (
    val customer: String,
    val finished: Boolean = false,
    val orderDate: LocalDate,
    val products: ArrayList<OrderProductTEntity> = ArrayList()
) {

    fun orderProduct(productName: String, amount: Int)
            = products.add(OrderProductTEntity(productName = productName, amount = amount))

}