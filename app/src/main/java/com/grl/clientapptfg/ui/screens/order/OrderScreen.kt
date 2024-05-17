package com.grl.clientapptfg.ui.screens.order

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.clientapptfg.core.Constants
import com.grl.clientapptfg.core.UserSession
import com.grl.clientapptfg.data.models.OrderModel
import com.grl.clientapptfg.ui.components.ConfirmationDialog
import com.grl.clientapptfg.ui.components.ConfirmationDialogWithNegative
import com.grl.clientapptfg.ui.components.LogoApp
import com.grl.clientapptfg.ui.components.OrderOptionsDialog
import com.grl.clientapptfg.ui.components.ProgressBarDialog
import com.grl.clientapptfg.ui.screens.tabs_menu.TabsMenuViewModel
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.blackSoft
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.mostazaSoft
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util

@Composable
fun OrderScreen(orderViewModel: OrderViewModel, tabsMenuViewModel: TabsMenuViewModel) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    val itemList = tabsMenuViewModel.itemList.observeAsState(initial = mutableListOf())
    val total = tabsMenuViewModel.totalPrice.observeAsState(initial = 0.0)
    val isDeleteVisible = orderViewModel.isDeleteVisible.observeAsState(initial = false)
    val isOrderPrepared = orderViewModel.isOrderPrepared.observeAsState(initial = false)
    val selectedItem = orderViewModel.selectedItem.observeAsState()
    val isOrderEmpty = orderViewModel.isOrderEmpty.observeAsState(initial = false)
    val isLoading = orderViewModel.isLoading.observeAsState(false)
    val isGoodRequest = orderViewModel.isGoodRequest.observeAsState(false)
    val paymentValue = orderViewModel.paymentValue.observeAsState(initial = Constants.CARD)
    val deliveryValue = orderViewModel.deliveryValue.observeAsState(initial = Constants.DELIVERY)
    val isOrderOptions = orderViewModel.isOrderOptions.observeAsState(initial = false)

    if (isDeleteVisible.value) {
        ConfirmationDialogWithNegative(
            onPositive = {
                tabsMenuViewModel.removeItem(selectedItem.value!!)
                tabsMenuViewModel.updateTotalPrice()
                orderViewModel.changeDeleteVisible(false)
            },
            title = "Vas a eliminar este producto",
            text = "¿Estas seguro de que quieres hacerlo?",
            onNegative = { orderViewModel.changeDeleteVisible(false) })
    }

    if (isOrderOptions.value) {
        OrderOptionsDialog(
            orderViewModel = orderViewModel,
            deliveryValue = deliveryValue.value,
            total = total.value,
            paymentValue = paymentValue.value
        ) {
            orderViewModel.changeIsOrderOptions(false)
            orderViewModel.createNewOrder(
                OrderModel(
                    price = if (deliveryValue.value == Constants.PICK) {
                        total.value
                    } else {
                        total.value + 1.5
                    },
                    items = itemList.value,
                    paymentMethod = paymentValue.value,
                    user = UserSession.getUser()!!,
                    state = Constants.PENDING,
                    delivery = deliveryValue.value
                )
            )
        }
    }

    if (isOrderPrepared.value) {
        ConfirmationDialogWithNegative(
            onPositive = {
                orderViewModel.changeOrderPrepared(false)
                orderViewModel.changeIsOrderOptions(true)
            },
            title = "Finalizar Pedido",
            text = "¿Quieres finalizar ya tu pedido?",
            onNegative = { orderViewModel.changeOrderPrepared(false) })
    }

    if (isOrderEmpty.value) {
        ConfirmationDialog(
            onClick = { orderViewModel.changeOrderEmpty(false) },
            title = "¡El pedido esta vacio!",
            text = "Añade al menos un producto al pedido"
        )
    }

    if (isGoodRequest.value) {
        ConfirmationDialog(
            onClick = {
                tabsMenuViewModel.cleanList()
                tabsMenuViewModel.updateTotalPrice()
                orderViewModel.changeGoodRequest(false)
            },
            title = "Pedido Realizado",
            text = "¡El pedido ha sido realizado con éxito!"
        )
    }

    if (isLoading.value) {
        ProgressBarDialog()
    }

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        val (appLogo, title, body, button, totalPrice) = createRefs()
        val topGuide = createGuidelineFromTop(0.03f)
        val startGuide = createGuidelineFromStart(0.05f)
        val endGuide = createGuidelineFromEnd(0.05f)
        LogoApp(modifier = Modifier.constrainAs(appLogo) {
            top.linkTo(topGuide)
            start.linkTo(startGuide)
        })
        Text(
            text = "Mi Pedido",
            fontWeight = FontWeight.Bold,
            fontFamily = aladinFont,
            fontSize = 50.sp,
            color = white,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(topGuide)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                })
        Text(
            text = "Precio Total: ${Util.formatDouble(total.value)}€",
            fontFamily = aladinFont,
            fontSize = 45.sp,
            color = mostaza,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .constrainAs(totalPrice) {
                    top.linkTo(title.bottom)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    bottom.linkTo(body.top)
                })
        if (itemList.value.isEmpty()) {
            Box(modifier = Modifier
                .constrainAs(body) {
                    top.linkTo(totalPrice.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(button.top)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                }) {
                Text(
                    text = "¡No has añadido nada todavía!",
                    modifier = Modifier.align(Alignment.Center),
                    fontFamily = aladinFont,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    color = mostaza
                )
            }
        } else {
            LazyColumn(
                Modifier
                    .constrainAs(body) {
                        top.linkTo(totalPrice.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(button.top)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }) {
                items(itemList.value) { item ->
                    Card(
                        border = BorderStroke(3.dp, mostaza),
                        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
                        colors = CardDefaults.cardColors(containerColor = black),
                        shape = RoundedCornerShape(
                            bottomStart = 8.dp,
                            bottomEnd = 40.dp,
                            topStart = 40.dp,
                            topEnd = 8.dp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(225.dp)
                            .padding(horizontal = 12.dp, vertical = 10.dp)
                    ) {
                        ConstraintLayout(Modifier.fillMaxSize()) {
                            val (cardTitle, image, increase, decrease, number, cardPrice) = createRefs()
                            val startLimit = createGuidelineFromStart(0.02f)
                            val endLimit = createGuidelineFromEnd(0.02f)
                            val topLimit = createGuidelineFromTop(0.02f)
                            Image(
                                painter = painterResource(id = item.product.photo),
                                contentDescription = "Item image",
                                modifier = Modifier
                                    .size(140.dp)
                                    .constrainAs(image) {
                                        start.linkTo(startLimit)
                                        top.linkTo(parent.top)
                                        bottom.linkTo(parent.bottom)
                                    })
                            Text(
                                text = item.product.name,
                                Modifier
                                    .padding(horizontal = 10.dp)
                                    .constrainAs(cardTitle) {
                                        top.linkTo(topLimit)
                                        start.linkTo(image.end)
                                        end.linkTo(endLimit)
                                        width = Dimension.fillToConstraints
                                    },
                                fontSize = 35.sp,
                                fontFamily = aladinFont,
                                color = mostaza,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                style = TextStyle(lineHeight = 30.sp)
                            )
                            Text(
                                text = "${Util.formatDouble(item.product.price * item.amount)}€",
                                Modifier
                                    .constrainAs(cardPrice) {
                                        bottom.linkTo(number.top)
                                        start.linkTo(decrease.end)
                                        end.linkTo(increase.start)
                                    }, fontFamily = aladinFont, fontSize = 50.sp, color = mostaza
                            )
                            Button(
                                onClick = {
                                    item.amount++
                                    tabsMenuViewModel.updateTotalPrice()
                                },
                                shape = CircleShape,
                                colors = ButtonColors(
                                    containerColor = mostaza,
                                    contentColor = black,
                                    disabledContentColor = mostaza,
                                    disabledContainerColor = granate
                                ),
                                modifier = Modifier
                                    .height(70.dp)
                                    .padding(bottom = 20.dp)
                                    .constrainAs(increase) {
                                        start.linkTo(number.end)
                                        bottom.linkTo(parent.bottom)
                                    }) {
                                Text(
                                    text = "+",
                                    color = black,
                                    fontSize = 40.sp,
                                    fontFamily = aladinFont,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                            Text(
                                text = item.amount.toString(), modifier = Modifier
                                    .padding(horizontal = 20.dp)
                                    .padding(bottom = 20.dp)
                                    .constrainAs(number) {
                                        start.linkTo(image.end)
                                        end.linkTo(endLimit)
                                        bottom.linkTo(parent.bottom)
                                    }, fontFamily = aladinFont, fontSize = 35.sp, color = mostaza
                            )
                            Button(
                                onClick = {
                                    if (item.amount - 1 == 0) {
                                        orderViewModel.setItemSelected(item)
                                        orderViewModel.changeDeleteVisible(true)
                                    } else {
                                        item.amount--
                                        tabsMenuViewModel.updateTotalPrice()
                                    }
                                },
                                shape = CircleShape,
                                colors = ButtonColors(
                                    containerColor = mostaza,
                                    contentColor = black,
                                    disabledContentColor = mostaza,
                                    disabledContainerColor = granate
                                ),
                                modifier = Modifier
                                    .height(70.dp)
                                    .padding(bottom = 20.dp)
                                    .constrainAs(decrease) {
                                        end.linkTo(number.start)
                                        bottom.linkTo(parent.bottom)
                                    }) {
                                Text(
                                    text = "-",
                                    color = black,
                                    fontSize = 40.sp,
                                    fontFamily = aladinFont,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
        }
        Button(onClick = {
            if (itemList.value.isEmpty()) {
                orderViewModel.changeOrderEmpty(true)
            } else {
                orderViewModel.changeOrderPrepared(true)
            }
        },
            colors = ButtonColors(
                contentColor = black,
                containerColor = mostaza,
                disabledContainerColor = mostazaSoft,
                disabledContentColor = blackSoft
            ), modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(vertical = 10.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    width = Dimension.fillToConstraints
                }) {
            Text(
                text = "Finalizar Pedido",
                fontFamily = aladinFont,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}