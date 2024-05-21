package com.grl.propietaryapptfg.data.models

data class OrderModel(
    var id: Int = 0,
    var price: Double,
    var paymentMethod: String,
    var items: List<ItemModel>,
    var user: UserModel?,
    var state: String,
    var delivery: String
)