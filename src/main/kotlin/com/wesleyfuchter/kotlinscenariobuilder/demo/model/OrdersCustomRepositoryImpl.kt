package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.SearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class OrdersCustomRepositoryImpl
    @Autowired constructor(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate): OrdersCustomRepository {

    override fun findOrdersWithCustomQuery(request: SearchRequest): List<OrderSearchResponse> {
        val (cityToDelivery, finished) = request

        val sql = """

            SELECT *
              FROM "order"

        """

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