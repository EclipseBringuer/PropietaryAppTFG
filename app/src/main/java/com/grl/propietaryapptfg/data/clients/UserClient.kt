package com.grl.propietaryapptfg.data.clients

import com.grl.propietaryapptfg.data.models.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserClient {
    @GET("user/{gmail}/{password}")
    suspend fun doLogin(
        @Path("gmail") gmail: String,
        @Path("password") password: String,
        @Query("token") token: String
    ): Response<UserModel>

    @POST("user/new")
    suspend fun createUser(
        @Query("token") token: String,
        @Body newUser: UserModel
    ): Response<UserModel>
}