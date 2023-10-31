package com.example.stockstudy.api

import com.example.stockstudy.model.normalization.DataSetupRequest
import com.example.stockstudy.model.normalization.DataType
import com.example.stockstudy.service.DataSetupService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/setUp")
class StockDataSetUpController(
    val dataSetupService: DataSetupService
) {

    @PostMapping
    fun setUp(@RequestBody request: DataSetupRequest) {
        if (request.request == DataType.UNNORMALIZED) {
            dataSetupService.setUpUnNormalizedStock()
        }
        if (request.request == DataType.NORMALIZED) {
            dataSetupService.setUpNormalizedStock()
        }
    }
}
