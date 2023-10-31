package com.example.stockstudy.repository

import com.example.stockstudy.model.normalization.NormalizedStock
import org.springframework.data.jpa.repository.JpaRepository

interface NormalizedStockRepository : JpaRepository<NormalizedStock, Long> {
}
