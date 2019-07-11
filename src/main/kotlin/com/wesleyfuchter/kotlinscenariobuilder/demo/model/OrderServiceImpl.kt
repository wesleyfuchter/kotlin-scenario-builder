package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl @Autowired constructor(private val orders: CustomersCustomRepository): OrderService {

    override fun searchByRequest(request: CustomerSearchRequest): List<OrderSearchResponse> {
        return orders.findCustomersWithOrdersByCustomQuery(request)
    }

}