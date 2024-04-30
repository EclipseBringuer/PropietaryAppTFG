package com.grl.clientapptfg.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grl.clientapptfg.core.UserSession
import com.grl.clientapptfg.ui.screens.login.ui.LoginScreen
import com.grl.clientapptfg.ui.screens.login.ui.LoginViewModel
import com.grl.clientapptfg.ui.screens.menu.Screen

@Composable
fun TabsMenuContent(navController: NavHostController, modifier: Modifier, loginViewModel:LoginViewModel) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
        modifier = modifier
    ) {
        composable(Screens.Home.route) {

        }
        composable(Screens.Tracking.route) {

        }
        composable(Screens.Orders.route) {

        }
        composable(Screens.Login.route) {
            if (UserSession.isLoggedIn())
                Screen()
            else
                LoginScreen(loginViewModel = loginViewModel)

        }
        composable(Screens.Menu.route) {
            Screen()
        }
    }
}