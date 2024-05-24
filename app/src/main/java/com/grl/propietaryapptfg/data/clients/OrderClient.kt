package com.grl.propietaryapptfg.data.clients

import com.grl.propietaryapptfg.data.models.OrderModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface OrderClient {
    @GET("order/getAllNotCompleted")
    suspend fun getAllNotCompleted(@Query("token") token: String): Response<List<OrderModel>>

    @PUT("order/updateState/{id}")
    suspend fun updateState(
        @Path("id") id: Int,
        @Query("state") state: String,
        @Query("token") token: String
    ): Response<OrderModel>
}