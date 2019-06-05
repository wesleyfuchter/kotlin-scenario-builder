package com.wesleyfuchter.kotlinscenariobuilder.demo

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "transaction")
data class Transaction(

        @Id
        @GeneratedValue
        val id: String,

        @Column(nullable = false)
        val value: Double,

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        val type: TransactionType,

        @Column(nullable = false)
        val date: LocalDateTime,

        @ManyToOne
        @JoinColumn(nullable = false, name = "category_id")
        val category: Category

)