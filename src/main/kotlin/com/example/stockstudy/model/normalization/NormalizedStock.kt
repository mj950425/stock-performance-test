package com.example.stockstudy.model.normalization

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "normalized_stock")
class NormalizedStock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "minimum_date")
    val mininumDate: LocalDate,

    @Column(name = "maximum_date")
    val maximumDate: LocalDate,

    @Column(name = "stock_bundle_id")
    val stockBundleId: Long
)
