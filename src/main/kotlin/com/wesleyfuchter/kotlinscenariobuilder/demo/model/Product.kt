package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import javax.persistence.*

@Entity
@Table(name = "product")
data class Product(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
        val id: Long? = null,

        @Column(nullable = false)
        val name: String,

        @ManyToOne
        @JoinColumn(name = "category_id")
        val category: ProductCategory

)