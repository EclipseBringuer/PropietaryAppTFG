package com.grl.clientapptfg.ui.screens.principalmenu.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.grl.clientapptfg.ui.components.TabsMenuContent
import com.grl.clientapptfg.ui.screens.login.ui.LoginViewModel
import com.grl.clientapptfg.ui.components.BottomNavigationItem

@Composable
fun TabsMenuScreen(tabsMenuViewModel: TabsMenuViewModel, loginViewModel: LoginViewModel) {
    val navigationSelectedItem: Int by tabsMenuViewModel.navigationSelectedItem.observeAsState(
        initial = 4
    )
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().getNavigationItems()
                    .forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = index == navigationSelectedItem,
                            label = {
                                Text(item.label)
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = item.label
                                )
                            },
                            onClick = {
                                tabsMenuViewModel.updateSelectedItem(index, item, navController)
                            }
                        )
                    }
            }
        }
    ) { paddingValues ->
        TabsMenuContent(
            navController = navController,
            modifier = Modifier.padding(paddingValues),
            loginViewModel = loginViewModel
        )
    }
}