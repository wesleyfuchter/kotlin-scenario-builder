package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.SearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Repository

@Repository
class OrdersImpl
    @Autowired constructor(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): Orders {

    override fun findOrdersWithCustomQuery(request: SearchRequest): List<OrderSearchResponse> {
        val (cityToDelivery, finished) = request

        val sql = """

            SELECT customer_name
              FROM order
              JOIN customer on order.customer_id = customer.id
             WHERE 1 = 1

        """.trimIndent()

         return namedParameterJdbcTemplate.query(sql, mapOf(
                "cityToDelivery" to cityToDelivery,
                "finished" to finished
        )) { resultSet, _ ->
             OrderSearchResponse.with {
                 orderCustomerName = resultSet.getString("customer_name")
             }
        }

    }

}