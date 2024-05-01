package com.grl.clientapptfg.data.repositories

import com.grl.clientapptfg.data.services.LoginService
import com.grl.clientapptfg.data.models.UserModel
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api: LoginService) {

    suspend fun doLogin(gmail: String, password: String): UserModel {
        return api.doLogin(gmail, password)
    }
}