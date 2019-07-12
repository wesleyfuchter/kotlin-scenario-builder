package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class OrderSearchResponse (

    var orderCustomerName: String = "",
    var orderDate: LocalDate? = null

) {

    fun title() = "Order to $orderCustomerName at ${DateTimeFormatter.ofPattern("dd/MM/yyyy").format(orderDate)}"

    companion object {
        fun with(buildScenario: OrderSearchResponse.() -> Unit) =
                OrderSearchResponse().apply(buildScenario)
    }
}