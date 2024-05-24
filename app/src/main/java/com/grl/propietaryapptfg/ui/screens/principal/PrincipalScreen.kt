package com.grl.propietaryapptfg.ui.screens.principal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.grl.propietaryapptfg.ui.screens.order_details.OrderDetailsScreen
import com.grl.propietaryapptfg.ui.screens.order_list.OrderListScreen
import com.grl.propietaryapptfg.ui.screens.order_list.OrderListViewModel

@Composable
fun PrincipalScreen(
    principalViewModel: PrincipalViewModel,
    orderListViewModel: OrderListViewModel,
) {
    val screenState = principalViewModel.screenState.observeAsState(initial = 1)
    when (screenState.value) {
        1 -> OrderListScreen(
            principalViewModel = principalViewModel,
            orderListViewModel = orderListViewModel
        )

        2 -> OrderDetailsScreen(principalViewModel = principalViewModel)
    }
}