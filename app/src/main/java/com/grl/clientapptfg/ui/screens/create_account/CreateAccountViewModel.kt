package com.grl.clientapptfg.ui.screens.create_account

import androidx.lifecycle.ViewModel
import com.grl.clientapptfg.data.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(userRepository: UserRepository) : ViewModel() {
}