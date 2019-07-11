package com.wesleyfuchter.kotlinscenariobuilder.demo.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "\"order\"")
data class Order(

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
        val id: Long? = null,

        @JoinColumn
        @ManyToOne
        val customer: Customer,

        @Column(nullable = false)
        val finished: Boolean = false,

        @Column(nullable = false)
        val orderDate: LocalDate,

        @OneToMany(cascade = [CascadeType.PERSIST])
        @JoinColumn(name = "order_id")
        val products: List<OrderProduct>

)