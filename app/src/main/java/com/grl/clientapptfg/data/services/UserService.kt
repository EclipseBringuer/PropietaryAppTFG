package com.grl.clientapptfg.data.services

import com.grl.clientapptfg.data.clients.UserClient
import com.grl.clientapptfg.data.models.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserService @Inject constructor(private val userClient: UserClient) {
    suspend fun doLogin(gmail: String, password: String): UserModel {
        return withContext(Dispatchers.IO) {
            val response = userClient.doLogin(gmail, password)
            response.body()!!
        }
    }
}