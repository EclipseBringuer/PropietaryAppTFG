package com.grl.clientapptfg.data.services

import com.grl.clientapptfg.core.Constants
import com.grl.clientapptfg.data.clients.ProductClient
import com.grl.clientapptfg.data.models.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductService @Inject constructor(private val productClient: ProductClient) {
    suspend fun getAllProducts(): List<ProductModel> {
        return withContext(Dispatchers.IO){
            val response = productClient.getAll(Constants.TOKEN)
            response.body()!!
        }
    }

    suspend fun getCategories(): List<String> {
        return withContext(Dispatchers.IO){
            val response = productClient.getCategories(Constants.TOKEN)
            response.body()!!
        }
    }
}