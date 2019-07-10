package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "order")
data class Order(

        @Id
        @GeneratedValue
        val id: Long,

        @JoinColumn
        @ManyToOne
        val customer: Customer,

        @Column(nullable = false)
        val finished: Boolean = false,

        @Column(nullable = false)
        val orderDate: LocalDate,

        @OneToMany
        @JoinColumn
        val products: List<OrderProduct>

)