package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import com.wesleyfuchter.kotlinscenariobuilder.demo.search.OrderSearchResponse

interface CustomerSearchService {

    fun searchByRequest(request: CustomerSearchRequest): List<OrderSearchResponse>

}