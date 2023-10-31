package com.example.stockstudy.service

import com.example.stockstudy.model.ServiceType
import com.example.stockstudy.model.StockWithOptimisticLock
import jakarta.persistence.EntityManager
import jakarta.persistence.OptimisticLockException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RdbOptimisticService(
    val entityManager: EntityManager
) : StockPerformanceTestService {

    @Transactional
    override fun test() {
        val stockId = 1
        val maxRetries = 3
        var currentTry = 0
        var waitTime = 50L

        while (true) {
            try {
                val stock = entityManager.find(StockWithOptimisticLock::class.java, stockId)
                stock.useStock()
                entityManager.flush()
                break
            } catch (e: OptimisticLockException) {
                currentTry++

                if (currentTry >= maxRetries) {
                    println("재고 감소 작업에 실패했습니다. 최대 재시도 횟수를 초과했습니다.")
                    throw RuntimeException("재고 감소 작업에 실패했습니다. 최대 재시도 횟수를 초과했습니다.")
                }

                Thread.sleep(waitTime)
                waitTime *= 2
            }
        }
    }

    override fun supports(serviceType: ServiceType): Boolean {
        return ServiceType.OPTIMISTIC == serviceType
    }
}
