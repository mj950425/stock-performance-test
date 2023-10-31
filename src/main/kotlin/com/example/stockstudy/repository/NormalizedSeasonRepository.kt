package com.example.stockstudy.repository

import com.example.stockstudy.model.normalization.NormalizedSeason
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface NormalizedSeasonRepository : JpaRepository<NormalizedSeason, Long> {
    fun findByStockIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
        stockId: Long,
        useDate: LocalDate,
        sameUseDate: LocalDate
    ): List<NormalizedSeason>
}
