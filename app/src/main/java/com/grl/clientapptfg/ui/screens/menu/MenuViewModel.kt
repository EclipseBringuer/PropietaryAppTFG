package com.grl.clientapptfg.ui.screens.menu

import androidx.lifecycle.ViewModel
import com.grl.clientapptfg.data.repositories.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {
}