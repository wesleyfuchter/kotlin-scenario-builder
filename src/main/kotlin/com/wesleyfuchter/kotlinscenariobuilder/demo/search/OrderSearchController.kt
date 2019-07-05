package com.wesleyfuchter.kotlinscenariobuilder.demo.search

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.Order
import com.wesleyfuchter.kotlinscenariobuilder.demo.model.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/orders-search")
class OrderSearchController @Autowired constructor(private val orderService: OrderService) {

    @PutMapping
    fun search(@RequestBody request: SearchRequest): ResponseEntity<Iterable<Order>>
            = ResponseEntity.ok(orderService.searchByRequest(request))

}