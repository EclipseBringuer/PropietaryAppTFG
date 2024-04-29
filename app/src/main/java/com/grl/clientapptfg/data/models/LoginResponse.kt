package com.grl.clientapptfg.data.models

data class LoginResponse(
    var id: Int,
    var name: String,
    var gmail: String,
    var password: String,
    var phone: String = "Vacio",
    var address: String = "Vacio"
)