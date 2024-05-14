package com.grl.clientapptfg.ui.screens.tracking

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.clientapptfg.ui.components.LogoApp
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util

@Composable
fun TrackingScreen(trackingViewModel: TrackingViewModel) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        val (title, logo, body) = createRefs()
        val topGuide = createGuidelineFromTop(0.03f)
        val startGuide = createGuidelineFromStart(0.05f)
        LogoApp(modifier = Modifier.constrainAs(logo) {
            top.linkTo(topGuide)
            start.linkTo(startGuide)
        })
        Text(text = "Seguimiento",
            fontFamily = aladinFont,
            color = white,
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(bottom = 30.dp)
                .constrainAs(title) {
                    top.linkTo(topGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
        Box(modifier = Modifier
            .constrainAs(body) {
                top.linkTo(title.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.fillToConstraints
                width = Dimension.fillToConstraints
            }) {
            Text(
                text = "¡No has pedido nada todavía!",
                modifier = Modifier.align(Alignment.Center),
                fontSize = 35.sp,
                fontFamily = aladinFont,
                color = mostaza
            )
        }
    }
}