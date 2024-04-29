package com.grl.clientapptfg.ui.screens.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grl.clientapptfg.data.repositories.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text:LiveData<String> = _text

    fun getUser() {
        viewModelScope.launch {
            _text.value = loginRepository.doLogin("gabrielrl2004@gmail.com", "15112004").toString()
        }
    }
}