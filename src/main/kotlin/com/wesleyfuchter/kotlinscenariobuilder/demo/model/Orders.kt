package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface Orders: CrudRepository<Order, Long>