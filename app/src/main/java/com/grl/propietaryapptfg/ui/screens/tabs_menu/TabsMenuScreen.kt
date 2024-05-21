package com.grl.propietaryapptfg.ui.screens.tabs_menu

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.grl.propietaryapptfg.ui.components.TabsMenuContent
import com.grl.propietaryapptfg.ui.screens.login.LoginViewModel
import com.grl.propietaryapptfg.data.models.BottomNavigationItem
import com.grl.propietaryapptfg.ui.screens.create_account.CreateAccountViewModel
import com.grl.propietaryapptfg.ui.screens.menu.MenuViewModel
import com.grl.propietaryapptfg.ui.screens.order.OrderViewModel
import com.grl.propietaryapptfg.ui.screens.profile.ProfileViewModel
import com.grl.propietaryapptfg.ui.screens.tracking.TrackingViewModel
import com.grl.propietaryapptfg.ui.screens.user_details.UserDetailsViewModel
import com.grl.propietaryapptfg.ui.theme.black
import com.grl.propietaryapptfg.ui.theme.granate
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun TabsMenuScreen(
    tabsMenuViewModel: TabsMenuViewModel,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel,
    menuViewModel: MenuViewModel,
    userDetailsViewModel: UserDetailsViewModel,
    createAccountViewModel: CreateAccountViewModel,
    trackingViewModel: TrackingViewModel,
    orderViewModel: OrderViewModel
) {
    val navigationSelectedItem: Int by tabsMenuViewModel.navigationSelectedItem.observeAsState(
        initial = 4
    )
    val navController = rememberNavController()
    val aladinFont = Util.loadFontFamilyFromAssets()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(containerColor = mostaza, contentColor = granate) {
                BottomNavigationItem().getNavigationItems()
                    .forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == navigationSelectedItem,
                            label = {
                                Text(
                                    item.label,
                                    fontFamily = aladinFont,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = item.label,
                                    Modifier.size(30.dp),
                                )
                            },
                            onClick = {
                                tabsMenuViewModel.updateSelectedItem(index, item, navController)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = granate,
                                selectedIconColor = mostaza,
                                unselectedIconColor = granate,
                                unselectedTextColor = black,
                                selectedTextColor = white
                            )
                        )
                    }
            }
        }
    ) { paddingValues ->
        TabsMenuContent(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            loginViewModel = loginViewModel,
            profileViewModel = profileViewModel,
            menuViewModel = menuViewModel,
            userDetailsViewModel = userDetailsViewModel,
            createAccountViewModel = createAccountViewModel,
            trackingViewModel = trackingViewModel,
            orderViewModel = orderViewModel,
            tabsMenuViewModel = tabsMenuViewModel
        )
    }
}