package com.grl.clientapptfg.ui.screens.tabs_menu

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
import com.grl.clientapptfg.ui.components.TabsMenuContent
import com.grl.clientapptfg.ui.screens.login.LoginViewModel
import com.grl.clientapptfg.ui.components.BottomNavigationItem
import com.grl.clientapptfg.ui.screens.profile.ProfileViewModel
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util

@Composable
fun TabsMenuScreen(
    tabsMenuViewModel: TabsMenuViewModel,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel
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
            profileViewModel = profileViewModel
        )
    }
}