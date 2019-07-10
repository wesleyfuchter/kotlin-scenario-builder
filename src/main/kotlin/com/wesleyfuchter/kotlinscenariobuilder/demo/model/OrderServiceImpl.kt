package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.SearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl @Autowired constructor(private val orders: Orders): OrderService {

    override fun searchByRequest(request: SearchRequest): List<OrderSearchResponse> {
        return orders.findOrdersWithCustomQuery(request)
    }

}