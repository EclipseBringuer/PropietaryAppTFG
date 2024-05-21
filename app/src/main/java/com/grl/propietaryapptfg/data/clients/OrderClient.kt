package com.grl.propietaryapptfg.data.clients

import com.grl.propietaryapptfg.data.models.OrderModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface OrderClient {
    @POST("order/new")
    suspend fun createOrder(
        @Query("token") token: String,
        @Body order: OrderModel
    ): Response<OrderModel>

    @GET("order/findByUser")
    suspend fun getOrdersByUser(
        @Query("token") token: String,
        @Query("id") id: Int
    ): Response<List<OrderModel>>
}