package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "order_product")
data class OrderProduct(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_product_id_seq")
        val id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "product_id")
        val product: Product,

        @Column(nullable = false)
        val amount: Int

)