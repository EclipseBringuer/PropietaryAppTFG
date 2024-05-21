package com.grl.propietaryapptfg.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.grl.propietaryapptfg.core.Screens
import com.grl.propietaryapptfg.ui.screens.create_account.CreateAccountViewModel
import com.grl.propietaryapptfg.ui.screens.login.LoginViewModel
import com.grl.propietaryapptfg.ui.screens.menu.MenuScreen
import com.grl.propietaryapptfg.ui.screens.menu.MenuViewModel
import com.grl.propietaryapptfg.ui.screens.order.OrderScreen
import com.grl.propietaryapptfg.ui.screens.order.OrderViewModel
import com.grl.propietaryapptfg.ui.screens.profile.ProfileScreen
import com.grl.propietaryapptfg.ui.screens.profile.ProfileViewModel
import com.grl.propietaryapptfg.ui.screens.start.StartScreen
import com.grl.propietaryapptfg.ui.screens.tabs_menu.TabsMenuViewModel
import com.grl.propietaryapptfg.ui.screens.tracking.TrackingScreen
import com.grl.propietaryapptfg.ui.screens.tracking.TrackingViewModel
import com.grl.propietaryapptfg.ui.screens.user_details.UserDetailsViewModel

@Composable
fun TabsMenuContent(
    navController: NavHostController,
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel,
    userDetailsViewModel: UserDetailsViewModel,
    menuViewModel: MenuViewModel,
    createAccountViewModel: CreateAccountViewModel,
    trackingViewModel: TrackingViewModel,
    orderViewModel: OrderViewModel,
    tabsMenuViewModel: TabsMenuViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route,
        modifier = modifier,
    ) {
        composable(Screens.Home.route) {
            StartScreen()
        }
        composable(Screens.Tracking.route) {
            TrackingScreen(trackingViewModel = trackingViewModel)
        }
        composable(Screens.Orders.route) {
            OrderScreen(orderViewModel = orderViewModel, tabsMenuViewModel)
        }
        composable(Screens.Login.route) {
            ProfileScreen(
                loginViewModel = loginViewModel,
                profileViewModel = profileViewModel,
                userDetailsViewModel = userDetailsViewModel,
                createAccountViewModel = createAccountViewModel,
                tabsMenuViewModel = tabsMenuViewModel,
                trackingViewModel = trackingViewModel
            )
        }
        composable(Screens.Menu.route) {
            MenuScreen(menuViewModel, tabsMenuViewModel)
        }
    }
}