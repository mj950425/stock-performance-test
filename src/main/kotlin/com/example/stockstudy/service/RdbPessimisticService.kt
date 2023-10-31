package com.example.stockstudy.service

import com.example.stockstudy.model.ServiceType
import com.example.stockstudy.model.Stock
import jakarta.persistence.EntityManager
import jakarta.persistence.LockModeType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RdbPessimisticService(
    val entityManager: EntityManager
) : StockPerformanceTestService {

    @Transactional
    override fun test() {
        val stockId = 1
        val lockedStock = entityManager.find(Stock::class.java, stockId, LockModeType.PESSIMISTIC_WRITE)
        lockedStock.useStock()
    }

    override fun supports(serviceType: ServiceType): Boolean {
        return ServiceType.PESSIMISTIC == serviceType
    }
}
