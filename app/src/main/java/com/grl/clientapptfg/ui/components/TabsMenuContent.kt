package com.grl.clientapptfg.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grl.clientapptfg.ui.screens.login.LoginViewModel
import com.grl.clientapptfg.ui.screens.menu.MenuScreen
import com.grl.clientapptfg.ui.screens.profile.ProfileScreen
import com.grl.clientapptfg.ui.screens.profile.ProfileViewModel

@Composable
fun TabsMenuContent(navController: NavHostController, modifier: Modifier, loginViewModel: LoginViewModel, profileViewModel: ProfileViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
        modifier = modifier,
    ) {
        composable(Screens.Home.route) {
            MenuScreen()
        }
        composable(Screens.Tracking.route) {
            MenuScreen()
        }
        composable(Screens.Orders.route) {
            MenuScreen()
        }
        composable(Screens.Login.route) {
            ProfileScreen(loginViewModel = loginViewModel, profileViewModel = profileViewModel)
        }
        composable(Screens.Menu.route) {
            MenuScreen()
        }
    }
}