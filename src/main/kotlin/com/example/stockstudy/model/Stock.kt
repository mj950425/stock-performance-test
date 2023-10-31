package com.example.stockstudy.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Stock(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    @Column(name = "registered_quantity")
    val registeredQuantity: Long,
    @Column(name = "used_quantity")
    var usedQuantity: Long,
    @Column(name = "remain_quantity")
    var remainQuantity: Long
) {
    fun useStock() {
        usedQuantity += 1
        remainQuantity -= 1
    }
}
