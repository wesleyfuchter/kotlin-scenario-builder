package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.SearchRequest

interface OrderService {

    fun searchByRequest(request: SearchRequest): List<Order>

}