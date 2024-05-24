package com.grl.propietaryapptfg.data.services

import com.grl.propietaryapptfg.core.Constants
import com.grl.propietaryapptfg.data.clients.OrderClient
import com.grl.propietaryapptfg.data.models.OrderModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OrderService @Inject constructor(private val orderClient: OrderClient) {
    suspend fun getAllNotCompleted(): List<OrderModel> {
        return withContext(Dispatchers.IO) {
            val response = orderClient.getAllNotCompleted(Constants.TOKEN)
            response.body()!!
        }
    }

    suspend fun updateState(id: Int, state: String):OrderModel {
        return withContext(Dispatchers.IO) {
            val response = orderClient.updateState(id, state, Constants.TOKEN)
            response.body()!!
        }
    }
}