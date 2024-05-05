package com.grl.clientapptfg.ui.screens.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.clientapptfg.data.models.ProductModel
import com.grl.clientapptfg.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private lateinit var allProduts: List<ProductModel>

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndex: LiveData<Int> = _selectedIndex

    fun changeSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    fun getCategories(): List<String> {
        var output: List<String> = listOf("")
        viewModelScope.launch {
            try {
                output = productRepository.getCategories()
            } catch (e: Exception) {
                print(e.stackTrace)
            }
        }
        return output
    }

    fun getProducts() {
        viewModelScope.launch {
            try {
                allProduts = productRepository.getAllProducts()
            } catch (e: Exception) {
                print(e.stackTrace)
            }
        }
    }

    fun filterByCategory(category: String) {
        val filteredList = mutableListOf<ProductModel>()
        allProduts.forEach { product ->
            if (product.category == category) {
                filteredList.add(product)
            }
        }
        _products.value = filteredList
    }
}