package com.grl.clientapptfg.core

import com.grl.clientapptfg.data.models.ProductModel
import com.grl.clientapptfg.data.models.UserModel

class UserSession {
    companion object {
        private var user: UserModel? = null
        private var productList: MutableList<ProductModel> = mutableListOf()
        fun setUser(user: UserModel?) {
            this.user = user
        }

        fun getUser(): UserModel? {
            return this.user
        }

        fun addProduct(product:ProductModel){
            productList.add(product)
        }

        fun removeProduct(product: ProductModel){
            productList.remove(product)
        }

        fun cleanList(){
            productList = mutableListOf()
        }
    }
}