package com.wesleyfuchter.kotlinscenariobuilder.demo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: CrudRepository<Transaction, String>