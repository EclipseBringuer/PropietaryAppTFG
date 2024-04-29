package com.grl.clientapptfg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.grl.clientapptfg.ui.screens.login.ui.LoginViewModel
import com.grl.clientapptfg.ui.screens.principalmenu.ui.TabsMenuScreen
import com.grl.clientapptfg.ui.screens.principalmenu.ui.TabsMenuViewModel
import com.grl.clientapptfg.ui.theme.ClientAppTFGTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val tabsMenuViewModel: TabsMenuViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientAppTFGTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabsMenuScreen(tabsMenuViewModel = tabsMenuViewModel, loginViewModel)
                }
            }
        }
    }
}