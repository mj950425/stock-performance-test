package com.example.stockstudy.repository

import com.example.stockstudy.model.normalization.UnNormalizedStock
import org.springframework.data.jpa.repository.JpaRepository

interface UnNormalizedStockRepository : JpaRepository<UnNormalizedStock, Long> {
}
