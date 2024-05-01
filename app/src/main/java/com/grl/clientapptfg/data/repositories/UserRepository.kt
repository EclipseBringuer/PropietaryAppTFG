package com.grl.clientapptfg.data.repositories

import com.grl.clientapptfg.data.services.UserService
import com.grl.clientapptfg.data.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService) {

    suspend fun doLogin(gmail: String, password: String): UserModel {
        return api.doLogin(gmail, password)
    }
}