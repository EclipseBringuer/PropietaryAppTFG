package com.grl.propietaryapptfg.ui.screens.order_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.propietaryapptfg.ui.components.LogoApp
import com.grl.propietaryapptfg.ui.screens.principal.PrincipalViewModel
import com.grl.propietaryapptfg.ui.theme.granate
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun OrderDetailsScreen(
    orderDetailsViewModel: OrderDetailsViewModel,
    principalViewModel: PrincipalViewModel
) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    val orderSelected by principalViewModel.orderSelected.observeAsState()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        val (logo, title, icon, divider) = createRefs()
        val startGuide = createGuidelineFromStart(0.05f)
        val endGuide = createGuidelineFromEnd(0.05f)
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
                .padding(top = 15.dp)
                .constrainAs(divider) {
                    top.linkTo(title.bottom)
                    end.linkTo(endGuide)
                    start.linkTo(startGuide)
                    width = Dimension.fillToConstraints
                })
    }
}