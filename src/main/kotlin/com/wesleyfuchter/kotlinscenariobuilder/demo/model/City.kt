package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "city")
data class City(

        @Id
        @GeneratedValue
        val id: Long,

        @Column(nullable = false)
        val name: String

)