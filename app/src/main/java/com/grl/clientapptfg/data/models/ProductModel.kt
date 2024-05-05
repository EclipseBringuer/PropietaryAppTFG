package com.grl.clientapptfg.data.models

data class ProductModel(
    var id: Int,
    var name: String,
    var price: Double,
    var photo: ByteArray,
    var description: String,
    var category: String
)