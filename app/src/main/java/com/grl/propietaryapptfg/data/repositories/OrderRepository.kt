package com.grl.propietaryapptfg.data.repositories

import com.grl.propietaryapptfg.data.models.OrderModel
import com.grl.propietaryapptfg.data.services.OrderService
import javax.inject.Inject

class OrderRepository @Inject constructor(private val api: OrderService) {
    suspend fun createNewOrder(newOrder: OrderModel): OrderModel {
        return api.createNewOrder(newOrder)
    }

    suspend fun getOrdersByUser(id: Int): List<OrderModel> {
        return api.getOrdersByUser(id)
    }
}