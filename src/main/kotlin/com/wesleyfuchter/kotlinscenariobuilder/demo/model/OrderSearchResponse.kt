package com.wesleyfuchter.kotlinscenariobuilder.demo.model

data class OrderSearchResponse (

    var orderCustomerName: String = ""

) {

    companion object {
        fun with(buildScenario: OrderSearchResponse.() -> Unit) =
                OrderSearchResponse().apply(buildScenario)
    }
}