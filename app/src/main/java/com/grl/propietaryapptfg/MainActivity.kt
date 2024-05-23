package com.grl.propietaryapptfg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.grl.propietaryapptfg.ui.screens.order_details.OrderDetailsViewModel
import com.grl.propietaryapptfg.ui.screens.order_list.OrderListViewModel
import com.grl.propietaryapptfg.ui.screens.principal.PrincipalScreen
import com.grl.propietaryapptfg.ui.screens.principal.PrincipalViewModel
import com.grl.propietaryapptfg.ui.theme.ClientAppTFGTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val principalViewModel: PrincipalViewModel by viewModels()
    private val orderDetailsViewModel: OrderDetailsViewModel by viewModels()
    private val orderListViewModel: OrderListViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClientAppTFGTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PrincipalScreen(
                        principalViewModel = principalViewModel,
                        orderListViewModel = orderListViewModel,
                        orderDetailsViewModel = orderDetailsViewModel
                    )
                }
            }
        }
    }
}