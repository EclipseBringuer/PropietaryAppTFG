package com.grl.clientapptfg.ui.screens.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.clientapptfg.data.models.OrderModel
import com.grl.clientapptfg.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackingViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    ViewModel() {
    private val _orders = MutableLiveData<List<OrderModel>>()
    val orders: LiveData<List<OrderModel>> = _orders

    private val _orderSelected = MutableLiveData<OrderModel>()
    val orderSelected: LiveData<OrderModel> = _orderSelected

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isFirst = MutableLiveData<Boolean>()
    val isFirst: LiveData<Boolean> = _isFirst

    fun changeIsFirst(boolean: Boolean) {
        _isFirst.value = boolean
    }

    fun changeOrderSelected(order: OrderModel) {
        _orderSelected.value = order
    }

    fun cleanOrderList() {
        _orders.value = listOf()
        _orderSelected.value = OrderModel(
            price = 0.0,
            paymentMethod = "",
            delivery = "",
            items = listOf(),
            state = "",
            user = null
        )
    }

    fun getOrdersByUser(id: Int) {
        if (_isFirst.value!!) {
            _isLoading.value = true
        }
        viewModelScope.launch {
            try {
                _orders.value = orderRepository.getOrdersByUser(id)
                if (_isFirst.value!!) {
                    _orderSelected.value = _orders.value!![0]
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (_isFirst.value!!) {
                    _isLoading.value = false
                    _isFirst.value = false
                }
            }
        }
    }
}