package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest

interface CustomersCustomRepository {

    fun findCustomersWithOrdersByCustomQuery(
            request: CustomerSearchRequest): List<OrderSearchResponse>


}