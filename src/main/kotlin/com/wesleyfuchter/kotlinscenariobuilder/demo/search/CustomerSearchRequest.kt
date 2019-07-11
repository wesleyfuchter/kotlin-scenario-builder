package com.wesleyfuchter.kotlinscenariobuilder.demo.search

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.City
import com.wesleyfuchter.kotlinscenariobuilder.demo.model.ProductCategory
import java.time.LocalDate

data class CustomerSearchRequest(

        val finished: Boolean,
        val cityToDelivery: City? = null,
        val startDate: LocalDate? = null,
        val endDate: LocalDate? = null,
        val productCategory: ProductCategory? = null

)