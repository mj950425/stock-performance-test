package com.example.stockstudy.service

import com.example.stockstudy.model.ServiceType

interface StockPerformanceTestService {
    fun supports(serviceType: ServiceType): Boolean
    fun test()
}
