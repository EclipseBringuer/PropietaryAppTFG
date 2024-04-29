package com.grl.clientapptfg.data.clients

import com.grl.clientapptfg.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LoginClient {
    @GET("user/{gmail}/{password}")
    suspend fun doLogin(
        @Path("gmail") gmail: String,
        @Path("password") password: String
    ): Response<LoginResponse>
}