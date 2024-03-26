package com.grl.clientapptfg.login.domain

import com.grl.clientapptfg.login.data.LoginRepository
import com.grl.clientapptfg.login.data.network.response.LoginResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {

    suspend operator fun invoke(gmail:String, password:String):LoginResponse{
        return repository.doLogin(gmail, password)
    }
}