package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import com.wesleyfuchter.kotlinscenariobuilder.demo.search.OrderSearchResponse

interface CustomersCustomRepository {

    fun findCustomersWithOrdersByCustomQuery(
            request: CustomerSearchRequest): List<OrderSearchResponse>


}