package com.wesleyfuchter.kotlinscenariobuilder.demo.search

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.Order

interface OrderSearchDAO {

    fun searchByRequest(request: SearchRequest): List<Order>

}