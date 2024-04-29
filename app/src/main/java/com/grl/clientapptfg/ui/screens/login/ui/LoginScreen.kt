package com.grl.clientapptfg.ui.screens.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.clientapptfg.R
import com.grl.clientapptfg.ui.theme.GreenLink

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.ic_good_logo_background))
    ) {
        val (appImage, title, newAcc, divider) = createRefs()
        val topGuide = createGuidelineFromTop(0.03f)
        val startGuide = createGuidelineFromStart(0.05f)
        val endGuide = createGuidelineFromEnd(0.05f)

        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "Imagen de la app",
            modifier = Modifier
                .size(60.dp)
                .clip(shape = RoundedCornerShape(15.dp))
                .constrainAs(appImage) {
                    top.linkTo(topGuide)
                    start.linkTo(startGuide)
                }
        )
        Text(
            text = "Bienvenido",
            Modifier
                .constrainAs(title) {
                    top.linkTo(topGuide)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    bottom.linkTo(appImage.bottom)
                },
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp)
                .constrainAs(newAcc) {
                    top.linkTo(title.bottom)
                    end.linkTo(endGuide)
                    start.linkTo(startGuide)
                }, horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Accede o crea ",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "una nueva cuenta",
                Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { },
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                color = GreenLink
            )
        }
        Row(
            Modifier
                .constrainAs(divider) {
                    top.linkTo(newAcc.bottom)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                }, verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                Modifier
                    .weight(1f)
                    .padding(start = 20.dp), color = GreenLink)
            Text(
                text = "O",
                Modifier.padding(horizontal = 15.dp),
                fontSize = 15.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            )
            HorizontalDivider(
                Modifier
                    .weight(1f)
                    .padding(end = 20.dp), color = GreenLink)
        }
    }
}