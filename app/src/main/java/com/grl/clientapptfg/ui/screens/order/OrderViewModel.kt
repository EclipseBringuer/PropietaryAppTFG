package com.grl.clientapptfg.ui.screens.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.clientapptfg.data.models.ItemModel
import com.grl.clientapptfg.data.models.OrderModel
import com.grl.clientapptfg.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    ViewModel() {
    private val _isDeleteVisible = MutableLiveData<Boolean>()
    val isDeleteVisible: LiveData<Boolean> = _isDeleteVisible

    private val _isOrderPrepared = MutableLiveData<Boolean>()
    val isOrderPrepared: LiveData<Boolean> = _isOrderPrepared

    private val _isOrderEmpty = MutableLiveData<Boolean>()
    val isOrderEmpty: LiveData<Boolean> = _isOrderEmpty

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _paymentValue = MutableLiveData<String>()
    val paymentValue: LiveData<String> = _paymentValue

    private val _deliveryValue = MutableLiveData<String>()
    val deliveryValue: LiveData<String> = _deliveryValue

    private val _isOrderOptions = MutableLiveData<Boolean>()
    val isOrderOptions: LiveData<Boolean> = _isOrderOptions

    private val _isGoodRequest = MutableLiveData<Boolean>()
    val isGoodRequest: LiveData<Boolean> = _isGoodRequest

    private val _selectedItem = MutableLiveData<ItemModel>()
    val selectedItem: LiveData<ItemModel> = _selectedItem

    fun changePaymentValue(value: String) {
        _paymentValue.value = value
    }

    fun changeDeliveryValue(value: String) {
        _deliveryValue.value = value
    }

    fun changeIsOrderOptions(boolean: Boolean) {
        _isOrderOptions.value = boolean
    }

    fun setItemSelected(item: ItemModel) {
        _selectedItem.value = item
    }

    fun changeDeleteVisible(boolean: Boolean) {
        _isDeleteVisible.value = boolean
    }

    fun changeOrderEmpty(boolean: Boolean) {
        _isOrderEmpty.value = boolean
    }

    fun changeOrderPrepared(boolean: Boolean) {
        _isOrderPrepared.value = boolean
    }

    fun changeGoodRequest(boolean: Boolean) {
        _isGoodRequest.value = boolean
    }

    fun createNewOrder(order: OrderModel) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val created = orderRepository.createNewOrder(order)
                if (created.id != 0) {
                    _isGoodRequest.value = true
                }
            } catch (e: Exception) {
                print(e.stackTrace)
            } finally {
                _isLoading.value = false
            }
        }
    }
}