package com.wesleyfuchter.kotlinscenariobuilder.demo

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "category")
data class Category(

        @Id
        @GeneratedValue
        val id: String,

        @Column(nullable = false)
        val name: String

)