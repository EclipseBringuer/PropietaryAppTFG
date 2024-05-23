package com.grl.propietaryapptfg.ui.screens.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grl.propietaryapptfg.data.models.OrderModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrincipalViewModel @Inject constructor() : ViewModel() {
    private val _screenState = MutableLiveData<Int>()
    val screenState: LiveData<Int> = _screenState

    private val _orderSelected = MutableLiveData<OrderModel>()
    val orderSelected: LiveData<OrderModel> = _orderSelected

    fun setOrderSelected(order: OrderModel) {
        _orderSelected.value = order
    }

    fun setScreenState(number: Int) {
        _screenState.value = number
    }
}