package com.grl.clientapptfg.data.clients

import com.grl.clientapptfg.data.models.ProductModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductClient {
    @GET("products/getAll")
    suspend fun getAll(@Query("token") token: String): Response<List<ProductModel>>

    @GET("products/getCategories")
    suspend fun getCategories(@Query("token") token: String): Response<List<String>>
}