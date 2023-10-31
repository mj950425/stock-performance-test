package com.example.stockstudy.service

import com.example.stockstudy.model.ServiceType
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service

/*
 * 남은 재고량을 redis에서 관리한다고 가정합니다
 * redis가 메모리 기반의 디비이기 때문에, 정확한 이력을 rdb에 남기기 위해서는 추가 작업이 필요할 수 있습니다.
 */
@Service
class RedissonAtomicCommandService(
    val redissonClient: RedissonClient
) : StockPerformanceTestService {
    private val stockKey = "stock:remain_quantity"

    override fun supports(serviceType: ServiceType): Boolean {
        return ServiceType.REDISSON_ATOMIC_COMMAND == serviceType
    }

    override fun test() {
        val remainQuantity = redissonClient.getAtomicLong(stockKey)

        while (true) {
            val currentValue = remainQuantity.get()

            if (currentValue <= 0) {
                println("재고가 부족합니다.")
                return
            }

            if (remainQuantity.compareAndSet(currentValue, currentValue - 1)) {
                break
            }
        }
    }
}
