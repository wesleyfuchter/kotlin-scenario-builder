package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import com.wesleyfuchter.kotlinscenariobuilder.demo.search.OrderSearchResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerSearchServiceImpl @Autowired constructor(private val orders: CustomersCustomRepository): CustomerSearchService {

    override fun searchByRequest(request: CustomerSearchRequest): List<OrderSearchResponse> {
        return orders.findCustomersWithOrdersByCustomQuery(request)
    }

}