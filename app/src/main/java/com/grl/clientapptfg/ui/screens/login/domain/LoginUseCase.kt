package com.grl.clientapptfg.ui.screens.login.domain

import com.grl.clientapptfg.data.repositories.LoginRepository
import com.grl.clientapptfg.data.models.LoginResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(gmail:String, password:String): LoginResponse {
        return repository.doLogin(gmail, password)
    }
}