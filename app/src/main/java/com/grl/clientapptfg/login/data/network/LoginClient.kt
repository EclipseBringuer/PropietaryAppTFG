package com.grl.clientapptfg.login.data.network

import com.grl.clientapptfg.login.data.network.response.LoginResponse
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