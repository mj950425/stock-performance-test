package com.example.stockstudy.repository

import com.example.stockstudy.model.normalization.UnNormalizedStock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate

interface UnNormalizedStockRepository : JpaRepository<UnNormalizedStock, Long> {
    @Query(
        value = """
        SELECT u.*
        FROM unnormalized_stock u
             JOIN JSON_TABLE(u.seasons, '$[*]'
                             COLUMNS (
                                 `from` TIMESTAMP PATH '$.from',
                                 `to` TIMESTAMP PATH '$.to'
                                 )
        ) AS j
                  ON TRUE
        WHERE :date BETWEEN j.`from` AND j.`to` AND u.stock_bundle_id = :stockBundleId
        """, nativeQuery = true
    )
    fun findByUsingDateAndStockBundleId(date: LocalDate, stockBundleId: Long): List<UnNormalizedStock>
}
