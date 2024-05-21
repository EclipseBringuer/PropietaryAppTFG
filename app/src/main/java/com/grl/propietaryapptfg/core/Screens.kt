package com.grl.propietaryapptfg.core

sealed class Screens(val route: String) {
    data object Login: Screens("login-route")
    data object Menu: Screens("menu-route")
    data object Account: Screens("account-route")
    data object Home: Screens("home-route")
    data object Tracking: Screens("tracking-route")
    data object Orders: Screens("orders-route")
}