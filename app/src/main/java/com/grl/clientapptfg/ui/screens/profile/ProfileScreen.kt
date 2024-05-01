package com.grl.clientapptfg.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.grl.clientapptfg.ui.screens.login.LoginScreen
import com.grl.clientapptfg.ui.screens.login.LoginViewModel
import com.grl.clientapptfg.ui.screens.user_details.UserDetailsScreen

@Composable
fun ProfileScreen(loginViewModel: LoginViewModel, profileViewModel: ProfileViewModel) {
    val isUserLogged = profileViewModel.isUserLogged.observeAsState(initial = false)
    if (!isUserLogged.value) {
        LoginScreen(loginViewModel = loginViewModel, profileViewModel = profileViewModel)
    } else {
        UserDetailsScreen()
    }
}