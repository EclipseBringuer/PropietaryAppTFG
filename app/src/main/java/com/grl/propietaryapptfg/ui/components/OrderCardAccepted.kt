package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.propietaryapptfg.R
import com.grl.propietaryapptfg.core.Constants
import com.grl.propietaryapptfg.data.models.OrderModel
import com.grl.propietaryapptfg.ui.theme.black
import com.grl.propietaryapptfg.ui.theme.blackSoft
import com.grl.propietaryapptfg.ui.theme.gray
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.ui.theme.mostazaSoft
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun OrderCardAccepted(
    order: OrderModel,
    aladinFont: FontFamily,
    onButton: () -> Unit,
    onIcon: () -> Unit
) {
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
            .height(270.dp)
            .padding(horizontal = 20.dp, vertical = 18.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (orderNumber, price, changeBtn, ico, state, stateLabel) = createRefs()
            val startGuide = createGuidelineFromStart(0.05f)
            val endGuide = createGuidelineFromEnd(0.05f)
            val topGuide = createGuidelineFromTop(0.05f)
            Text(
                text = "${order.id} Nº",
                fontFamily = aladinFont,
                color = mostaza,
                fontSize = 40.sp,
                style = TextStyle(lineHeight = 30.sp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .constrainAs(orderNumber) {
                        top.linkTo(topGuide)
                        start.linkTo(startGuide)
                    }
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { onIcon() }
                    .constrainAs(ico) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(changeBtn.top)
                    }) {
                Icon(
                    painter = painterResource(id = R.drawable.list),
                    contentDescription = "Lista de items",
                    tint = gray,
                    modifier = Modifier
                        .padding(vertical = 5.dp)
                        .size(55.dp)
                )
                Text(
                    text = "Info",
                    fontFamily = aladinFont,
                    color = gray,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Text(
                text = "${Util.formatDouble(order.price)}€",
                fontFamily = aladinFont,
                color = mostaza,
                fontSize = 40.sp,
                style = TextStyle(lineHeight = 30.sp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 20.dp)
                    .constrainAs(price) {
                        top.linkTo(topGuide)
                        end.linkTo(endGuide)
                    }
            )
            Text(
                text = "Estado:",
                fontFamily = aladinFont,
                color = white,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .constrainAs(stateLabel) {
                        start.linkTo(state.start)
                        bottom.linkTo(state.top)
                    }
            )
            Text(
                text = if (order.state == Constants.PREPARATION) "Preparación" else "Entrega",
                fontFamily = aladinFont,
                color = mostaza,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .constrainAs(state) {
                        top.linkTo(changeBtn.top)
                        start.linkTo(parent.start)
                        bottom.linkTo(changeBtn.bottom)
                    }
            )
            Button(
                onClick = { onButton() },
                colors = ButtonColors(
                    containerColor = mostaza,
                    contentColor = black,
                    disabledContentColor = blackSoft,
                    disabledContainerColor = mostazaSoft
                ),
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .height(65.dp)
                    .constrainAs(changeBtn) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(state.end)
                        width = Dimension.fillToConstraints
                    }) {
                Text(
                    text = "Cambiar Estado",
                    fontFamily = aladinFont,
                    fontSize = 27.sp,
                    style = TextStyle(lineHeight = 22.sp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}