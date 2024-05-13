package com.grl.clientapptfg.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.grl.clientapptfg.ui.screens.create_account.CreateAccountScreen
import com.grl.clientapptfg.ui.screens.create_account.CreateAccountViewModel
import com.grl.clientapptfg.ui.screens.login.LoginScreen
import com.grl.clientapptfg.ui.screens.login.LoginViewModel
import com.grl.clientapptfg.ui.screens.tabs_menu.TabsMenuViewModel
import com.grl.clientapptfg.ui.screens.user_details.UserDetailsScreen
import com.grl.clientapptfg.ui.screens.user_details.UserDetailsViewModel

@Composable
fun ProfileScreen(
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel,
    userDetailsViewModel: UserDetailsViewModel,
    createAccountViewModel: CreateAccountViewModel,
    tabsMenuViewModel: TabsMenuViewModel
) {
    val screenState = profileViewModel.screenState.observeAsState(initial = 1)
    when (screenState.value) {
        1 -> LoginScreen(loginViewModel = loginViewModel, profileViewModel = profileViewModel)
        2 -> UserDetailsScreen(userDetailsViewModel, profileViewModel, tabsMenuViewModel)
        3 -> CreateAccountScreen(createAccountViewModel = createAccountViewModel, profileViewModel = profileViewModel)
    }
}