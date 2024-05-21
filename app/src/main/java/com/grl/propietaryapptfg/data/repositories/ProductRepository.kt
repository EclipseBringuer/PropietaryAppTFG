package com.grl.propietaryapptfg.data.repositories

import com.grl.propietaryapptfg.data.models.ProductModel
import com.grl.propietaryapptfg.data.services.ProductService
import javax.inject.Inject

class ProductRepository @Inject constructor(private val api: ProductService) {
    suspend fun getAllProducts(): List<ProductModel> {
        return api.getAllProducts()
    }

    suspend fun getCategories(): List<String> {
        return api.getCategories()
    }
}