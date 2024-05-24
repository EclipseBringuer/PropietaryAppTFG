package com.grl.propietaryapptfg.ui.screens.order_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.propietaryapptfg.core.Constants
import com.grl.propietaryapptfg.data.models.OrderModel
import com.grl.propietaryapptfg.data.repositories.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderListViewModel @Inject constructor(private val orderRepository: OrderRepository) :
    ViewModel() {

    private lateinit var allOrders: List<OrderModel>

    private val _isLoading = MutableLiveData<Boolean>().apply { value = true }
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isAccepting = MutableLiveData<Boolean>().apply { value = false }
    val isAccepting: LiveData<Boolean> = _isAccepting

    private val _isCanceling = MutableLiveData<Boolean>().apply { value = false }
    val isCanceling: LiveData<Boolean> = _isCanceling

    private val _isChanging = MutableLiveData<Boolean>().apply { value = false }
    val isChanging: LiveData<Boolean> = _isChanging

    private val _isCompleted = MutableLiveData<Boolean>().apply { value = false }
    val isCompleted: LiveData<Boolean> = _isCompleted

    private val _orders = MutableLiveData<List<OrderModel>>()
    val orders: LiveData<List<OrderModel>> = _orders

    private val _tabs =
        MutableLiveData<List<String>>().apply { value = Constants.Companion.Tabs.getListOfTabs() }
    val tabs: LiveData<List<String>> = _tabs

    private val _selectedIndex = MutableLiveData<Int>().apply { value = 0 }
    val selectedIndex: LiveData<Int> = _selectedIndex

    private var isFirstLoad = true

    init {
        viewModelScope.launch {
            getOrdersNotCompleted()
            startPollingForOrders()
        }
    }

    fun setIsChanging(boolean: Boolean) {
        _isChanging.value = boolean
    }

    fun setIsCompleted(boolean: Boolean) {
        _isCompleted.value = boolean
    }

    fun setIsAccepting(boolean: Boolean) {
        _isAccepting.value = boolean
    }

    fun setIsCanceling(boolean: Boolean) {
        _isCanceling.value = boolean
    }

    fun changeSelectedIndex(index: Int) {
        _selectedIndex.value = index
        filterByTab(index)
    }

    fun acceptOrder(order: OrderModel) {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Mostrar barra de carga
                orderRepository.updateState(order.id, Constants.PREPARATION)
                getOrdersNotCompleted()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Ocultar barra de carga
            }
        }
    }

    fun deliveryOrder(order: OrderModel) {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Mostrar barra de carga
                orderRepository.updateState(order.id, Constants.DELIVERY)
                getOrdersNotCompleted()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Ocultar barra de carga
                _isCompleted.value = true
            }
        }
    }

    fun completeOrder(order: OrderModel) {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Mostrar barra de carga
                orderRepository.updateState(order.id, Constants.COMPLETED)
                getOrdersNotCompleted()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Ocultar barra de carga
                _isCompleted.value = true
            }
        }
    }

    fun cancelOrder(order: OrderModel) {
        viewModelScope.launch {
            try {
                _isLoading.value = true // Mostrar barra de carga
                orderRepository.updateState(order.id, Constants.CANCELED)
                getOrdersNotCompleted()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false // Ocultar barra de carga
            }
        }
    }

    private fun getOrdersNotCompleted() {
        viewModelScope.launch {
            try {
                if (isFirstLoad) {
                    _isLoading.value = true // Mostrar carga solo en la primera vez
                }
                allOrders = orderRepository.getAllNotCompleted()
                filterByTab(selectedIndex.value ?: 0) // Filtrar según la pestaña seleccionada
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (isFirstLoad) {
                    _isLoading.value = false
                    isFirstLoad = false // Ya no es la primera vez que se muestra la pantalla
                }
            }
        }
    }

    private suspend fun startPollingForOrders() {
        while (true) {
            delay(8000)
            getOrdersNotCompleted()
        }
    }

    private fun filterByTab(selected: Int) {
        viewModelScope.launch {
            val filteredList = if (selected == 0) {
                allOrders.filter { it.state == Constants.PENDING }
            } else {
                allOrders.filter { it.state != Constants.PENDING }
            }
            _orders.value = filteredList
        }
    }
}