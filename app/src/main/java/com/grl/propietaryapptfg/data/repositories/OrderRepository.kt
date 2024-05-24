package com.grl.propietaryapptfg.data.repositories

import com.grl.propietaryapptfg.data.models.OrderModel
import com.grl.propietaryapptfg.data.services.OrderService
import javax.inject.Inject

class OrderRepository @Inject constructor(private val api: OrderService) {
    suspend fun getAllNotCompleted(): List<OrderModel> {
        return api.getAllNotCompleted()
    }

    suspend fun updateState(id: Int, state: String): OrderModel {
        return api.updateState(id, state)
    }
}