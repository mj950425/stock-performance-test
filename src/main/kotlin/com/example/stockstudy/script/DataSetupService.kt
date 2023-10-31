package com.example.stockstudy.script

import com.example.stockstudy.model.Stock
import com.example.stockstudy.repository.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DataSetupService(
    private val stockRepository: StockRepository
) {

    @Transactional
    fun setUpdata() {
        for (i in 1..10000) {
            stockRepository.save(
                Stock(
                    registeredQuantity = 10000,
                    remainQuantity = 10000,
                    usedQuantity = 0,
                )
            )
        }
    }
}
