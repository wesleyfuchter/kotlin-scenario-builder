package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest

interface OrderService {

    fun searchByRequest(request: CustomerSearchRequest): List<OrderSearchResponse>

}