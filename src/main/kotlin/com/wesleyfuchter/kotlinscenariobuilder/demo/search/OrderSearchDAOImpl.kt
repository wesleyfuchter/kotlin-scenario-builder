package com.wesleyfuchter.kotlinscenariobuilder.demo.search

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.Order
import org.springframework.stereotype.Repository

@Repository
class OrderSearchDAOImpl: OrderSearchDAO {

    override fun searchByRequest(request: SearchRequest): List<Order> {

        return listOf()
    }
}