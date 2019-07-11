package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "city")
data class City(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_id_seq")
        val id: Long? = null,

        @Column(nullable = false)
        val name: String

)