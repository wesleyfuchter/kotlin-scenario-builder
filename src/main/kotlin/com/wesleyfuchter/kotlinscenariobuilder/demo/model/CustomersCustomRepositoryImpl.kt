package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import com.wesleyfuchter.kotlinscenariobuilder.demo.search.CustomerSearchRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

@Repository
class CustomersCustomRepositoryImpl
@Autowired constructor(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) : CustomersCustomRepository {

    override fun findCustomersWithOrdersByCustomQuery(request: CustomerSearchRequest): List<OrderSearchResponse> {
        val (finished, cityToDelivery, startDate, endDate, productCategory) = request

        val sql = """

            SELECT customer.name as customer_name
              FROM "order"
              JOIN customer on "order".customer_id = customer.id
              JOIN order_product on "order".id = order_product.order_id
              JOIN product on order_product.product_id = product.id
             WHERE customer.city_id = :cityToDelivery
               AND "order".finished = :finished
               AND "order".order_date between :startDate and :endDate
               AND product.category_id = :productCategory

        """

        return namedParameterJdbcTemplate.queryForList(sql, mapOf(
                "cityToDelivery" to cityToDelivery?.id,
                "finished" to finished,
                "startDate" to startDate,
                "endDate" to endDate,
                "productCategory" to productCategory?.id
        )).map { resultMap ->
            OrderSearchResponse.with {
                orderCustomerName = resultMap["customer_name"] as String
            }
        }

    }

}