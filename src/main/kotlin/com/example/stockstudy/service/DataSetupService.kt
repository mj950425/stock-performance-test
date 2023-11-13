package com.example.stockstudy.service

import com.example.stockstudy.model.normalization.NormalizedSeason
import com.example.stockstudy.model.normalization.NormalizedStock
import com.example.stockstudy.model.normalization.UnNormalizedStock
import com.example.stockstudy.repository.NormalizedSeasonRepository
import com.example.stockstudy.repository.NormalizedStockRepository
import com.example.stockstudy.repository.UnNormalizedStockRepository
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class DataSetupService(
    private val entityManager: EntityManager,
    private val normalizedStockRepository: NormalizedStockRepository,
    private val normalizedSeasonRepository: NormalizedSeasonRepository,
    private val unNormalizedStockRepository: UnNormalizedStockRepository
) {

    @Transactional
    fun setUpNormalizedStock() {
        val baseDate = LocalDate.now()

        val stocks = mutableListOf<NormalizedStock>()
        val seasons = mutableListOf<NormalizedSeason>()

        for (stockBundleId in 101L..500L) {
            for (stockCount in 1L..2L) {

                val stock = NormalizedStock(
                    mininumDate = baseDate.plusDays(1L),
                    maximumDate = baseDate.plusDays(730L),
                    stockBundleId = stockBundleId
                )

                stocks.add(stock)

                for (day in 1L + (stockCount - 1) * 730..730L + (stockCount - 1) * 730) {
                    val targetDate = baseDate.plusDays(day)
                    val normalizedSeason = NormalizedSeason(
                        startDate = targetDate,
                        endDate = targetDate,
                        stock = stock
                    )
                    seasons.add(normalizedSeason)
                }

                if (stockBundleId % 10 == 0L && stockCount == 2L) {
                    println("[${stockBundleId * 2 * 730}] stock saved...")

                    normalizedStockRepository.saveAll(stocks)
                    normalizedSeasonRepository.saveAll(seasons)
                    entityManager.flush()
                    entityManager.clear()

                    stocks.clear()
                    seasons.clear()
                }
            }
        }

        normalizedStockRepository.saveAll(stocks)
        normalizedSeasonRepository.saveAll(seasons)
    }

    @Transactional
    fun setUpUnNormalizedStock() {
        val baseDate = LocalDate.now()
        val stocks = mutableListOf<UnNormalizedStock>()

        for (stockBundleId in 101L..500) {
            for (stockCount in 1L..2L) {
                val seasons = mutableListOf<String>()

                for (day in 1L + (stockCount - 1) * 730..730L + (stockCount - 1) * 730) {
                    val targetDate = baseDate.plusDays(day)
                    seasons.add("""{"from": "$targetDate", "to": "$targetDate"}""")
                }

                val result = seasons.joinToString(separator = ", ", prefix = "[", postfix = "]")

                stocks.add(UnNormalizedStock(seasons = result, stockBundleId = stockBundleId))

                if (stockBundleId % 10 == 0L && stockCount == 2L) {
                    println("[${stockBundleId * 2 * 730}] stock saved...")

                    unNormalizedStockRepository.saveAll(stocks)
                    entityManager.flush()
                    entityManager.clear()

                    stocks.clear()
                }
            }
        }

        unNormalizedStockRepository.saveAll(stocks)
    }
}
