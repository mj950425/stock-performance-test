package com.example.stockstudy.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DistributeLockTransactionProxy {

    @Transactional
    fun proceed(
        joinPoint: ProceedingJoinPoint
    ): Any? {
        return joinPoint.proceed()
    }
}
