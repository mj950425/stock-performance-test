package com.example.stockstudy.model.normalization

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "unnormalized_stock")
class UnNormalizedStock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,

    @Column(name = "seasons", columnDefinition = "TEXT")
    val seasons: String,

    @Column(name = "stock_bundle_id")
    val stockBundleId: Long
)
