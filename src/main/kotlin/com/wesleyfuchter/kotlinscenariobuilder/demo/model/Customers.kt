package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Customers: CrudRepository<Customer, Long>