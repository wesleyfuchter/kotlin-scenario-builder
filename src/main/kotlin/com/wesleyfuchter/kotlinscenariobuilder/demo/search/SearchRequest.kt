package com.wesleyfuchter.kotlinscenariobuilder.demo.search

import java.time.LocalDate

data class SearchRequest(

        val finished: Boolean,
        val cityToDelivery: String? = null,
        val productCategory: String? = null,
        val startDate: LocalDate? = null,
        val endDate: LocalDate? = null

)