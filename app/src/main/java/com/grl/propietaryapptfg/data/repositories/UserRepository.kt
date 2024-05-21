package com.grl.propietaryapptfg.data.repositories

import com.grl.propietaryapptfg.data.services.UserService
import com.grl.propietaryapptfg.data.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(private val api: UserService) {
    suspend fun doLogin(gmail: String, password: String): UserModel {
        return api.doLogin(gmail, password)
    }

    suspend fun createNewUser(newUser: UserModel): UserModel {
        return api.createNewUser(newUser)
    }
}