package com.grl.clientapptfg.core

import com.grl.clientapptfg.data.models.Item
import com.grl.clientapptfg.data.models.LoginResponse

class UserSession {
    companion object{
        private var listOfItems:List<Item> = mutableListOf()
        private var user:LoginResponse? = null
        fun addItem(item: Item){

        }

        fun setUser(user:LoginResponse){
            this.user = user
        }
    }
}