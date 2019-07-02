package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "product_category")
data class ProductCategory(

        @Id
        @GeneratedValue
        val id: Long,

        @Column(nullable = false)
        val name: String

)