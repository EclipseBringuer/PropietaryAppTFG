package com.grl.clientapptfg.ui.screens.menu

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.clientapptfg.R
import com.grl.clientapptfg.core.Constants
import com.grl.clientapptfg.data.models.ProductModel
import com.grl.clientapptfg.data.repositories.ProductRepository
import com.grl.clientapptfg.utils.Util
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
    private lateinit var allProduts: List<ProductModel>

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _badLogged = MutableLiveData<Boolean>()
    val badLogged: LiveData<Boolean> = _badLogged

    private val _isVisible = MutableLiveData<Boolean>()
    val isVisible: LiveData<Boolean> = _isVisible

    private val _isFirstTime = MutableLiveData<Boolean>()
    val isFirstTime: LiveData<Boolean> = _isFirstTime

    private val _categories = MutableLiveData<List<String>>()
    val categories: LiveData<List<String>> = _categories

    private val _products = MutableLiveData<List<ProductModel>>()
    val products: LiveData<List<ProductModel>> = _products

    private val _selectedIndex = MutableLiveData<Int>()
    val selectedIndex: LiveData<Int> = _selectedIndex

    private val _productSelected = MutableLiveData<ProductModel>()
    val productSelected: LiveData<ProductModel> = _productSelected

    fun changeFirstTime(boolean: Boolean) {
        _isFirstTime.value = boolean
    }

    fun changeBadLogged(boolean: Boolean) {
        _badLogged.value = boolean
    }

    fun setActualProduct(product: ProductModel) {
        _productSelected.value = product
    }

    fun setIsVisible(boolean: Boolean) {
        _isVisible.value = boolean
    }

    fun changeSelectedIndex(index: Int) {
        _selectedIndex.value = index
    }

    /* Posible función a futuro para hacer las categorias dinamicas al contenido de la BD

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
    */

    fun getProducts() {
        viewModelScope.launch {
            try {
                var list = Util.getUpdatePhotos()
                print(list)
                allProduts = productRepository.getAllProducts()
                filterByCategory(Constants.Companion.Category.KEBAB)
            } catch (e: Exception) {
                print(e.stackTrace)
            } finally {
                _isLoading.value = false
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

    @Composable
    fun getPhotoByCategory(category: String): Painter {
        return when (category) {
            Constants.Companion.Category.KEBAB -> painterResource(id = R.drawable.kebab1)
            Constants.Companion.Category.DURUM -> painterResource(id = R.drawable.durum1)
            Constants.Companion.Category.BEBIDA -> painterResource(id = R.drawable.bebidas1)
            Constants.Companion.Category.RACION -> painterResource(id = R.drawable.racion1)
            Constants.Companion.Category.MENU -> painterResource(id = R.drawable.segundo)
            Constants.Companion.Category.LAHMACUN -> painterResource(id = R.drawable.lahmacun_categoria)
            Constants.Companion.Category.HAMBURGUESA -> painterResource(id = R.drawable.hamburguesa1)
            else -> painterResource(id = R.drawable.tortilla)
        }
    }
}