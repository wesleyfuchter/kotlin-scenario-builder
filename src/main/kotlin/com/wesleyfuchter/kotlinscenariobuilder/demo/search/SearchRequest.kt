package com.wesleyfuchter.kotlinscenariobuilder.demo.search

data class SearchRequest(

        val ordersIds: List<Long> = listOf(),
        val customersIds: List<Long> = listOf(),
        val citiesIds: List<Long> = listOf(),
        val productsIds: List<Long> = listOf(),
        val productCategoryIds: List<Long> = listOf(),
        val finished: Boolean

)