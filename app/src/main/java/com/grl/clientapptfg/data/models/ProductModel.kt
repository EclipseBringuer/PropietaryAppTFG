package com.grl.clientapptfg.data.models

data class ProductModel(
    var id: Int,
    var name: String,
    var price: Double,
    var photo: Int = 0,
    var description: String,
    var category: String
)