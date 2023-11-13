package com.example.stockstudy.service

import com.example.stockstudy.repository.UnNormalizedStockRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class UnNormalizedStockService(
    val unNormalizedStockRepository: UnNormalizedStockRepository
) {

    fun findByStockIdAndUsingDate() {
        unNormalizedStockRepository.findByUsingDateAndStockBundleId(LocalDate.of(2023, 12, 24), 99)
    }
}
