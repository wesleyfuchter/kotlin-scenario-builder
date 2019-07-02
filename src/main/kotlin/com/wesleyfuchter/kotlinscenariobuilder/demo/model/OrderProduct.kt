package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "order_product")
data class OrderProduct(

        @Id
        @GeneratedValue
        val id: Long,

        @JoinColumn
        @ManyToOne
        val order: Order,

        @JoinColumn
        @ManyToOne
        val product: Product,

        @Column(nullable = false)
        val amount: Int

)