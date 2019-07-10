package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

interface DatabaseSchema {
    val cities: Cities
    val customers: Customers
    val productCategories: ProductCategories
    val products: Products
    val orders: Orders
}

@Component
class DatabaseSchemaImpl @Autowired constructor
(
        override val cities: Cities,
        override val customers: Customers,
        override val productCategories: ProductCategories,
        override val products: Products,
        override val orders: Orders

) : DatabaseSchema