package com.grl.clientapptfg.data.repositories

import com.grl.clientapptfg.data.models.ProductModel
import com.grl.clientapptfg.data.services.ProductService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val api: ProductService) {
    suspend fun getAllProducts(): List<ProductModel> {
        return api.getAllProducts()
    }
}