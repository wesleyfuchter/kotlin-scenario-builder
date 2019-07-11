package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "product_category")
data class ProductCategory(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_id_seq")
        val id: Long? = null,

        @Column(nullable = false)
        val name: String

)