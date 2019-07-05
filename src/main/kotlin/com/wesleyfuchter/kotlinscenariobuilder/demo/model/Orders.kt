package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.SearchRequest

interface Orders {

    fun findOrdersWithCustomQuery(request: SearchRequest): List<OrderSearchResponse>


}