package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate

@Repository
class CustomersCustomRepositoryImpl
@Autowired constructor(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : CustomersCustomRepository {

    override fun findCustomersWithOrdersByCustomQuery(request: CustomerSearchRequest): List<OrderSearchResponse> {
        val (finished, cityToDelivery, startDate, endDate, productCategory) = request

        val sql = """

            SELECT customer.name as customer_name, "order".order_date
              FROM "order", customer
             WHERE "order".customer_id = customer.id
               AND "order".finished = :finished
               AND customer.city_id = coalesce(:cityToDelivery, customer.city_id)
               AND "order".order_date between coalesce(:startDate, TO_DATE('1900/01/01', 'YYYY/MM/DD'))
                                            and coalesce(:endDate, TO_DATE('2999/12/31', 'YYYY/MM/DD'))
               AND exists (SELECT 1
                             FROM order_product, product
                            WHERE "order".id = order_product.order_id
                              AND order_product.product_id = product.id
                              AND product.category_id = coalesce(:productCategory, product.category_id))

        """.trimIndent()

        return namedParameterJdbcTemplate.queryForList(sql, mapOf(
                "cityToDelivery" to cityToDelivery?.id,
                "finished" to finished,
                "startDate" to startDate,
                "endDate" to endDate,
                "productCategory" to productCategory?.id
        )).map { resultMap ->
            OrderSearchResponse.with {
                orderCustomerName = resultMap["customer_name"] as String
                orderDate = (resultMap["order_date"] as Date).toLocalDate()
            }
        }

    }

}