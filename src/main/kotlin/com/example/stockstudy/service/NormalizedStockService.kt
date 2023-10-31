package com.example.stockstudy.service

import com.example.stockstudy.repository.NormalizedSeasonRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class NormalizedStockService(
    val normalizedSeasonRepository: NormalizedSeasonRepository
) {
    fun findByStockIdAndUsingDate() {
        normalizedSeasonRepository
            .findByStockIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                1,
                LocalDate.of(2023, 12, 1),
                LocalDate.of(2023, 12, 1)
            )
    }
}
