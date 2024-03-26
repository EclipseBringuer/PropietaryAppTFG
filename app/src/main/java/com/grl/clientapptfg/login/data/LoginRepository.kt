package com.grl.clientapptfg.login.data

import com.grl.clientapptfg.login.data.network.LoginService
import com.grl.clientapptfg.login.data.network.response.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {

    suspend fun doLogin(gmail: String, password: String): LoginResponse {
        return api.doLogin(gmail, password)
    }
}