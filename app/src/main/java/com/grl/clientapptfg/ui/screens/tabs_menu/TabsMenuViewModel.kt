package com.grl.clientapptfg.ui.screens.tabs_menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.grl.clientapptfg.ui.components.BottomNavigationItem
import javax.inject.Inject

class TabsMenuViewModel @Inject constructor() : ViewModel() {
    private val _navigationSelectedItem = MutableLiveData<Int>()
    val navigationSelectedItem: LiveData<Int> = _navigationSelectedItem

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
}