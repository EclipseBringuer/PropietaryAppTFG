package com.grl.clientapptfg.ui.screens.tabs_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.grl.clientapptfg.data.models.BottomNavigationItem
import com.grl.clientapptfg.data.models.ItemModel
import com.grl.clientapptfg.data.models.ProductModel
import javax.inject.Inject

class TabsMenuViewModel @Inject constructor() : ViewModel() {
    private val _navigationSelectedItem = MutableLiveData<Int>()
    val navigationSelectedItem: LiveData<Int> = _navigationSelectedItem

    private val _itemList = MutableLiveData<MutableList<ItemModel>>()
    val itemList: LiveData<MutableList<ItemModel>> = _itemList

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: LiveData<Double> = _totalPrice

    fun updateSelectedItem(index: Int, item: BottomNavigationItem, navController:NavHostController) {
        _navigationSelectedItem.value = index
        navController.navigate(item.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun addProduct(product: ProductModel) {
        if(!_itemList.isInitialized){
            _itemList.value = mutableListOf()
            _itemList.value!!.add(ItemModel(amount = 1, product = product))
        }else {
            val existingItem = _itemList.value!!.find { it.product == product }
            if (existingItem != null) {
                existingItem.amount++
            } else {
                _itemList.value!!.add(ItemModel(amount = 1, product = product))
            }
        }
    }

    fun removeItem(item:ItemModel){
        _itemList.value!!.remove(item)
    }

    fun updateTotalPrice() {
        var price = 0.0
        _itemList.value!!.forEach { item ->
            price += item.amount * item.product.price
        }
        _totalPrice.value = price
    }

    fun cleanList() {
        _itemList.value = mutableListOf()
    }
}