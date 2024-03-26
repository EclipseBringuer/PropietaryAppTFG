package com.grl.clientapptfg.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.clientapptfg.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text:LiveData<String> = _text

    fun getUser() {
        viewModelScope.launch {
            _text.value = loginUseCase("gabrielrl2004@gmail.com", "15112004").toString()
        }
    }
}