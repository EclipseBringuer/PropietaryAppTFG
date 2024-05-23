package com.grl.propietaryapptfg.ui.screens.principal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PrincipalViewModel @Inject constructor() : ViewModel() {
    private val _screenState = MutableLiveData<Int>()
    val screenState: LiveData<Int> = _screenState

    fun setScreenState(number: Int) {
        _screenState.value = number
    }
}