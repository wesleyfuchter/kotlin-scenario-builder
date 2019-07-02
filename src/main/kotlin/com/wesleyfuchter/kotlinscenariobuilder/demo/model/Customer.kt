package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "customer")
data class Customer(

        @Id
        @GeneratedValue
        val id: Long,

        @Column(nullable = false)
        val name: String,

        @ManyToOne
        @JoinColumn(name = "city_id")
        val city: City

)