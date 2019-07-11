package com.wesleyfuchter.kotlinscenariobuilder.demo.search

import com.wesleyfuchter.kotlinscenariobuilder.demo.model.OrderSearchResponse
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
    fun search(@RequestBody request: CustomerSearchRequest): ResponseEntity<Iterable<OrderSearchResponse>>
            = ResponseEntity.ok(orderService.searchByRequest(request))

}