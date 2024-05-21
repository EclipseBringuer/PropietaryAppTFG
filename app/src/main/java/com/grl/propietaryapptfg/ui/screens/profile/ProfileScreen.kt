package com.grl.propietaryapptfg.ui.screens.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.grl.propietaryapptfg.ui.screens.create_account.CreateAccountScreen
import com.grl.propietaryapptfg.ui.screens.create_account.CreateAccountViewModel
import com.grl.propietaryapptfg.ui.screens.login.LoginScreen
import com.grl.propietaryapptfg.ui.screens.login.LoginViewModel
import com.grl.propietaryapptfg.ui.screens.tabs_menu.TabsMenuViewModel
import com.grl.propietaryapptfg.ui.screens.tracking.TrackingViewModel
import com.grl.propietaryapptfg.ui.screens.user_details.UserDetailsScreen
import com.grl.propietaryapptfg.ui.screens.user_details.UserDetailsViewModel

@Composable
fun ProfileScreen(
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel,
    userDetailsViewModel: UserDetailsViewModel,
    createAccountViewModel: CreateAccountViewModel,
    tabsMenuViewModel: TabsMenuViewModel,
    trackingViewModel: TrackingViewModel
) {
    val screenState = profileViewModel.screenState.observeAsState(initial = 1)
    when (screenState.value) {
        1 -> LoginScreen(loginViewModel = loginViewModel, profileViewModel = profileViewModel)
        2 -> UserDetailsScreen(userDetailsViewModel, profileViewModel, tabsMenuViewModel, trackingViewModel)
        3 -> CreateAccountScreen(createAccountViewModel = createAccountViewModel, profileViewModel = profileViewModel)
    }
}