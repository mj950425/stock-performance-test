package com.example.stockstudy.service

import com.example.stockstudy.annotation.DistributeLock
import com.example.stockstudy.model.ServiceType
import com.example.stockstudy.repository.StockRepository
import org.springframework.stereotype.Service

@Service
class RedisWithDistributionLockService(
    val stockRepository: StockRepository
) : StockPerformanceTestService {
    override fun supports(serviceType: ServiceType): Boolean {
        return ServiceType.DISTRIBUTION == serviceType
    }

    @DistributeLock("lock:stock:1")
    override fun test() {
        try {
            val stock = stockRepository.findById(1).get()
            stock.useStock()
            stockRepository.save(stock)
        } catch (e: Exception) {
            println("예외 발생: ${e.message}")
        }
    }
}
