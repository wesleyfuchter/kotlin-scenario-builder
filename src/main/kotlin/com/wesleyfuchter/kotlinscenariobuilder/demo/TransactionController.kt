package com.wesleyfuchter.kotlinscenariobuilder.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/t")
class TransactionController @Autowired constructor(val transactions: Transactions) {

    @GetMapping
    fun findAll(): ResponseEntity<Iterable<Transaction>>
            = ResponseEntity.ok(transactions.findAll())

}