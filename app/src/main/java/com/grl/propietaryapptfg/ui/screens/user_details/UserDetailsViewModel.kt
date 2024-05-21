package com.grl.propietaryapptfg.ui.screens.user_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor() : ViewModel() {
    private val _closeSession = MutableLiveData<Boolean>()
    val closeSession: LiveData<Boolean> = _closeSession

    fun changeCloseSession(boolean: Boolean){
        _closeSession.value = boolean
    }
}