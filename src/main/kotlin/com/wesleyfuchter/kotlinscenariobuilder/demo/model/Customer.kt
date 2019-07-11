package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_id_seq")
        val id: Long? = null,

        @Column(nullable = false)
        val name: String,

        @ManyToOne
        @JoinColumn(name = "city_id")
        val city: City

)