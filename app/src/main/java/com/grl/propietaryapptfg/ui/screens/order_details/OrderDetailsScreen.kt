package com.grl.propietaryapptfg.ui.screens.order_details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.propietaryapptfg.core.Constants
import com.grl.propietaryapptfg.ui.components.ItemCard
import com.grl.propietaryapptfg.ui.components.LogoApp
import com.grl.propietaryapptfg.ui.components.StyledText
import com.grl.propietaryapptfg.ui.screens.principal.PrincipalViewModel
import com.grl.propietaryapptfg.ui.theme.granate
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun OrderDetailsScreen(principalViewModel: PrincipalViewModel) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    val itemState = rememberLazyListState()
    val orderSelected by principalViewModel.orderSelected.observeAsState()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        val (logo, title, icon, divider,
            userTitle, userName, userTelf,
            userAdd, itemsTitle, totalPrice,
            itemList, payment, delivery) = createRefs()
        val startGuide = createGuidelineFromStart(0.02f)
        val endGuide = createGuidelineFromEnd(0.02f)
        LogoApp(modifier = Modifier.constrainAs(logo) {
            top.linkTo(title.top)
            start.linkTo(startGuide)
            bottom.linkTo(title.bottom)
            end.linkTo(title.start)
        })
        Text(
            text = "Detalles del pedido ${orderSelected!!.id} Nº",
            fontFamily = aladinFont,
            color = white,
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            style = TextStyle(lineHeight = 50.sp),
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(logo.end)
                    end.linkTo(icon.start)
                    width = Dimension.fillToConstraints
                })
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Icono de cierre",
            tint = mostaza,
            modifier = Modifier
                .size(45.dp)
                .constrainAs(icon) {
                    top.linkTo(title.top)
                    end.linkTo(endGuide)
                    bottom.linkTo(title.bottom)
                    start.linkTo(title.end)
                }
                .clickable {
                    principalViewModel.setScreenState(1)
                })
        HorizontalDivider(
            color = mostaza,
            thickness = 3.dp,
            modifier = Modifier
                .padding(vertical = 15.dp)
                .constrainAs(divider) {
                    top.linkTo(title.bottom)
                    end.linkTo(endGuide)
                    start.linkTo(startGuide)
                    width = Dimension.fillToConstraints
                })
        Text(text = "Detalles del cliente:",
            fontFamily = aladinFont,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = mostaza,
            modifier = Modifier
                .padding(bottom = 13.dp)
                .constrainAs(userTitle) {
                    top.linkTo(divider.bottom)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                })
        StyledText(
            fieldText = "- Nombre: ",
            valueText = orderSelected!!.user.name,
            modifier = Modifier.constrainAs(userName) {
                top.linkTo(userTitle.bottom)
                start.linkTo(startGuide)
            })
        StyledText(
            fieldText = "- Teléfono: ",
            valueText = orderSelected!!.user.phone,
            modifier = Modifier.constrainAs(userTelf) {
                top.linkTo(userName.bottom)
                start.linkTo(startGuide)
            })
        StyledText(fieldText = "- Dirección: ",
            valueText = orderSelected!!.user.address,
            modifier = Modifier.constrainAs(userAdd) {
                top.linkTo(userTelf.bottom)
                start.linkTo(startGuide)
            })
        StyledText(fieldText = "- Método de pago: ",
            valueText = if (orderSelected!!.paymentMethod == Constants.CARD) "Tarjeta" else "Efectivo",
            modifier = Modifier.constrainAs(payment) {
                top.linkTo(userAdd.bottom)
                start.linkTo(startGuide)
            })
        StyledText(fieldText = "- Entrega: ",
            valueText = if (orderSelected!!.delivery == Constants.PICK) "Recogida" else "Domicilio",
            modifier = Modifier.constrainAs(delivery) {
                top.linkTo(payment.bottom)
                start.linkTo(startGuide)
            })
        Text(text = "Contenido del pedido:",
            fontFamily = aladinFont,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            color = mostaza,
            modifier = Modifier
                .padding(vertical = 13.dp)
                .constrainAs(itemsTitle) {
                    top.linkTo(delivery.bottom)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                })
        LazyRow(
            state = itemState,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .constrainAs(itemList) {
                    top.linkTo(itemsTitle.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(totalPrice.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }) {
            items(items = orderSelected!!.items, key = { item -> item.id }) { item ->
                ItemCard(item)
            }
        }
        val text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = mostaza)) {
                append("Total: ")
            }
            withStyle(style = SpanStyle(color = white)) {
                append("${Util.formatDouble(orderSelected!!.price)}€")
            }
        }
        Text(
            text = text,
            fontSize = 45.sp,
            fontFamily = aladinFont,
            modifier = Modifier
                .padding(top = 12.dp)
                .constrainAs(totalPrice) {
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
    BackHandler {
        principalViewModel.setScreenState(1)
    }
}