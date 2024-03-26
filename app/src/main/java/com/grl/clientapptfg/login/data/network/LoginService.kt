package com.grl.clientapptfg.login.data.network

import com.grl.clientapptfg.login.data.network.response.LoginResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient) {
    suspend fun doLogin(gmail: String, password: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            val response = loginClient.doLogin(gmail, password)
            response.body()!!
        }
    }
}