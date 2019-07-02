package com.wesleyfuchter.kotlinscenariobuilder.demo.model

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

        @OneToMany
        @JoinColumn
        val products: List<OrderProduct>

)