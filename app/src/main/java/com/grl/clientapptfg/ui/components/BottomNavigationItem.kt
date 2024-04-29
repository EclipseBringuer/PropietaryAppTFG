package com.grl.clientapptfg.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label: String = "",
    val icon: ImageVector = Icons.Filled.Home,
    val route: String = ""
) {

    fun getNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Inicio",
                icon = Icons.Filled.Home,
                route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Carta",
                icon = Icons.Filled.Menu,
                route = Screens.Menu.route
            ),
            BottomNavigationItem(
                label = "Pedido",
                icon = Icons.Filled.ShoppingCart,
                route = Screens.Orders.route
            ),
            BottomNavigationItem(
                label = "Seguir",
                icon = Icons.Filled.LocationOn,
                route = Screens.Tracking.route
            ),
            BottomNavigationItem(
                label = "Perfil",
                icon = Icons.Filled.AccountCircle,
                route = Screens.Login.route
            )
        )
    }
}