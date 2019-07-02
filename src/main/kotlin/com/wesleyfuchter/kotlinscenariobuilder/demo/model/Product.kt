package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(

        @Id
        @GeneratedValue
        val id: Long,

        @Column(nullable = false)
        val name: String,

        @ManyToOne
        @JoinColumn(name = "category_id")
        val category: ProductCategory

)