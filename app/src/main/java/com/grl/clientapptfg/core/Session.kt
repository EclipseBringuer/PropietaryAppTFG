package com.grl.clientapptfg.core

import com.grl.clientapptfg.data.models.Item

class UserSession {
    companion object{
        private var logged:Boolean = false
        private var listOfItems:List<Item> = mutableListOf()

        fun isLoggedIn():Boolean{
            return  logged
        }

        fun addItem(item: Item){

        }
    }
}