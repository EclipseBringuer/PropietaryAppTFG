package com.grl.clientapptfg.core

import com.grl.clientapptfg.data.models.Item
import com.grl.clientapptfg.data.models.UserModel

class UserSession {
    companion object{
        private var listOfItems:List<Item> = mutableListOf()
        private var user:UserModel? = null
        fun addItem(item: Item){

        }

        fun setUser(user:UserModel){
            this.user = user
        }
    }
}