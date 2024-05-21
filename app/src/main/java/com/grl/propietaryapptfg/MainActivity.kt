package com.grl.propietaryapptfg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.grl.propietaryapptfg.ui.screens.create_account.CreateAccountViewModel
import com.grl.propietaryapptfg.ui.screens.login.LoginViewModel
import com.grl.propietaryapptfg.ui.screens.menu.MenuViewModel
import com.grl.propietaryapptfg.ui.screens.order.OrderViewModel
import com.grl.propietaryapptfg.ui.screens.profile.ProfileViewModel
import com.grl.propietaryapptfg.ui.screens.tabs_menu.TabsMenuScreen
import com.grl.propietaryapptfg.ui.screens.tabs_menu.TabsMenuViewModel
import com.grl.propietaryapptfg.ui.screens.tracking.TrackingViewModel
import com.grl.propietaryapptfg.ui.screens.user_details.UserDetailsViewModel
import com.grl.propietaryapptfg.ui.theme.ClientAppTFGTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tabsMenuViewModel: TabsMenuViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()
    private val profileViewModel: ProfileViewModel by viewModels()
    private val menuViewModel: MenuViewModel by viewModels()
    private val userDetailsViewModel: UserDetailsViewModel by viewModels()
    private val createAccountViewModel: CreateAccountViewModel by viewModels()
    private val orderViewModel:OrderViewModel by viewModels()
    private val trackingViewModel:TrackingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientAppTFGTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabsMenuScreen(
                        tabsMenuViewModel = tabsMenuViewModel,
                        loginViewModel = loginViewModel,
                        profileViewModel = profileViewModel,
                        menuViewModel = menuViewModel,
                        userDetailsViewModel = userDetailsViewModel,
                        createAccountViewModel = createAccountViewModel,
                        orderViewModel = orderViewModel,
                        trackingViewModel = trackingViewModel
                    )
                }
            }
        }
    }
}