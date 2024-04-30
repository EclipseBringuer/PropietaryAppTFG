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
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository) :
    ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isVisible = MutableLiveData<Boolean>()
    val isVisible: LiveData<Boolean> = _isVisible

    fun changeVisibility(visibility: Boolean){
        _isVisible.value = !visibility
    }

    fun setEmail(newText: String) {
        _email.value = newText
    }

    fun setPassword(newText: String) {
        _password.value = newText
    }

    fun getUser() {
        viewModelScope.launch {
            _email.value = loginRepository.doLogin("gabrielrl2004@gmail.com", "15112004").toString()
        }
    }
}