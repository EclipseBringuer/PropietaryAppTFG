package com.grl.clientapptfg.data.repositories

import com.grl.clientapptfg.data.services.LoginService
import com.grl.clientapptfg.data.models.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {

    suspend fun doLogin(gmail: String, password: String): LoginResponse {
        return api.doLogin(gmail, password)
    }
}