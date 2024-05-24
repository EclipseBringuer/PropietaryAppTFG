package com.grl.propietaryapptfg.ui.screens.order_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.propietaryapptfg.core.Constants
import com.grl.propietaryapptfg.ui.components.CategoryItem
import com.grl.propietaryapptfg.ui.components.ChangeStateDialog
import com.grl.propietaryapptfg.ui.components.ConfirmationDialogWithNegative
import com.grl.propietaryapptfg.ui.components.EmptyBox
import com.grl.propietaryapptfg.ui.components.LogoApp
import com.grl.propietaryapptfg.ui.components.OrderCardAccepted
import com.grl.propietaryapptfg.ui.components.OrderCardPending
import com.grl.propietaryapptfg.ui.components.ProgressBarDialog
import com.grl.propietaryapptfg.ui.screens.principal.PrincipalViewModel
import com.grl.propietaryapptfg.ui.theme.granate
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun OrderListScreen(
    principalViewModel: PrincipalViewModel,
    orderListViewModel: OrderListViewModel
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth / 2
    val aladinFont = Util.loadFontFamilyFromAssets()
    val isLoading by orderListViewModel.isLoading.observeAsState(true)
    val orders by orderListViewModel.orders.observeAsState(initial = emptyList())
    val selectedIndex by orderListViewModel.selectedIndex.observeAsState(initial = 0)
    val tabs by orderListViewModel.tabs.observeAsState(initial = Constants.Companion.Tabs.getListOfTabs())
    val isAccepting by orderListViewModel.isAccepting.observeAsState(initial = false)
    val isCanceling by orderListViewModel.isCanceling.observeAsState(initial = false)
    val isChanging by orderListViewModel.isChanging.observeAsState(initial = false)
    val orderSelected by principalViewModel.orderSelected.observeAsState()
    val stateOrders = rememberLazyListState()

    if (isLoading) {
        ProgressBarDialog()
    }

    if (isAccepting) {
        ConfirmationDialogWithNegative(
            onPositive = {
                orderListViewModel.acceptOrder(orderSelected!!)
                orderListViewModel.setIsAccepting(false)
            },
            title = "¿Quieres aceptar el pedido?",
            text = "Pulsa aceptar para confirmar el pedido ${orderSelected!!.id} Nº"
        ) {
            orderListViewModel.setIsAccepting(false)
        }
    }

    if (isCanceling) {
        ConfirmationDialogWithNegative(
            onPositive = {
                orderListViewModel.cancelOrder(orderSelected!!)
                orderListViewModel.setIsCanceling(false)
            },
            title = "¿Quieres cancelar el pedido?",
            text = "Pulsa aceptar para cancelar el pedido ${orderSelected!!.id} Nº"
        ) {
            orderListViewModel.setIsCanceling(false)
        }
    }

    if (isChanging) {
        ChangeStateDialog()
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        val (body, surface) = createRefs()
        Surface(
            shadowElevation = 10.dp,
            color = granate,
            modifier = Modifier
                .padding(bottom = 8.dp)
                .constrainAs(surface) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(body.top)
                }
        ) {
            ConstraintLayout {
                val (logo, title, bar) = createRefs()
                val startGuide = createGuidelineFromStart(0.05f)
                LogoApp(modifier = Modifier.constrainAs(logo) {
                    top.linkTo(parent.top)
                    start.linkTo(startGuide)
                    end.linkTo(title.start)
                })
                Text(
                    text = "Lista de Pedidos",
                    fontFamily = aladinFont,
                    color = white,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .constrainAs(title) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        })
                LazyRow(
                    modifier = Modifier
                        .constrainAs(bar) {
                            top.linkTo(title.bottom)
                        }
                        .fillMaxWidth()
                        .height(90.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    itemsIndexed(tabs, key = { _, tab -> tab.hashCode() }) { index, tab ->
                        CategoryItem(
                            name = tab,
                            onClick = {
                                orderListViewModel.changeSelectedIndex(index)
                            },
                            isSelected = index == selectedIndex,
                            modifier = Modifier.width(itemWidth),
                        )
                    }
                }
            }
        }
        if (orders.isEmpty()) {
            EmptyBox(modifier = Modifier
                .constrainAs(body) {
                    top.linkTo(surface.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                })
        } else {
            LazyColumn(
                modifier = Modifier.constrainAs(body) {
                    top.linkTo(surface.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
                state = stateOrders
            ) {
                items(orders, key = { order -> order.id }) { order ->
                    if (selectedIndex == 0) {
                        OrderCardPending(
                            order,
                            aladinFont,
                            onAccept = {
                                principalViewModel.setOrderSelected(order)
                                orderListViewModel.setIsAccepting(true)
                            },
                            onCancel = {
                                principalViewModel.setOrderSelected(order)
                                orderListViewModel.setIsCanceling(true)
                            },
                            onIcon = {
                                principalViewModel.setOrderSelected(order)
                                principalViewModel.setScreenState(2)
                            })
                    } else {
                        OrderCardAccepted(
                            order,
                            aladinFont,
                            onButton = {
                                principalViewModel.setOrderSelected(order)
                                orderListViewModel.setIsChanging(true)
                            },
                            onIcon = {
                                principalViewModel.setOrderSelected(order)
                                principalViewModel.setScreenState(2)
                            })
                    }
                }
            }
        }
    }
}