package com.example.stockstudy.api

import com.example.stockstudy.model.ServiceTypeRequest
import com.example.stockstudy.service.NormalizedStockService
import com.example.stockstudy.service.StockPerformanceTestService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

@RestController
@RequestMapping("/test/")
class StockPerformanceTestController(
    val stockServices: List<StockPerformanceTestService>,
    val normalizedStockService: NormalizedStockService
) {
    @PostMapping
    fun testUseStockPerformance(@RequestBody request: ServiceTypeRequest): Long {
        val service = stockServices.find { it.supports(request.type) }!!

        val executor = Executors.newFixedThreadPool(10)
        val tasks = mutableListOf<Callable<Unit>>()
        for (i: Int in 1..1000) {
            tasks.add(Callable {
                service.test()
            })
        }
        return measureTimeMillis {
            executor.invokeAll(tasks)
            executor.shutdown()
        }
    }

    @GetMapping
    fun testNormalizedStockPerformance(@RequestBody request: ServiceTypeRequest): Long {
        return measureTimeMillis {
            normalizedStockService.findByStockIdAndUsingDate()
        }
    }
}
