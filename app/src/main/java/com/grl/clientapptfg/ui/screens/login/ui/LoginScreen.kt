package com.grl.clientapptfg.ui.screens.login.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.clientapptfg.R
import com.grl.clientapptfg.ui.theme.PurpleGrey40
import com.grl.clientapptfg.ui.theme.PurpleGrey80
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util

@Composable
fun LoginScreen(loginViewModel: LoginViewModel) {
    val email = loginViewModel.email.observeAsState("")
    val password = loginViewModel.password.observeAsState("")
    val aladinFont = Util.loadFontFamilyFromAssets()
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.ic_good_logo_background))
    ) {
        val (appImage, title, newAcc, divider, fields) = createRefs()
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
                .width(200.dp)
                .constrainAs(title) {
                    top.linkTo(topGuide)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                    bottom.linkTo(appImage.bottom)
                },
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            fontFamily = aladinFont,
            color = white
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
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = aladinFont,
                color = white
            )
            Text(
                text = "una nueva cuenta",
                Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { },
                fontSize = 25.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = aladinFont,
                textDecoration = TextDecoration.Underline,
                color = mostaza
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
                    .padding(start = 20.dp), color = mostaza
            )
            Text(
                text = "O",
                Modifier.padding(horizontal = 15.dp),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = aladinFont,
                textAlign = TextAlign.Center,
                color = white
            )
            HorizontalDivider(
                Modifier
                    .weight(1f)
                    .padding(end = 20.dp), color = mostaza
            )
        }
        Column(Modifier.constrainAs(fields) {
            top.linkTo(divider.bottom)
            start.linkTo(startGuide)
            end.linkTo(endGuide)
        }) {
            OutlinedTextField(
                value = email.value,
                onValueChange = { loginViewModel.setEmail(it) },
                Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 50.dp, bottom = 40.dp)
                    .fillMaxWidth()
                    .height(70.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, color = black),
                placeholder = {
                    Text(text = "Correo electrónico", fontSize = 25.sp, fontFamily = aladinFont, color = PurpleGrey80)
                }, colors = TextFieldDefaults.colors(focusedContainerColor = white,
                    unfocusedContainerColor = white,
                    disabledContainerColor = white,
                    focusedIndicatorColor = mostaza)
            )
            OutlinedTextField(
                value = password.value,
                onValueChange = { loginViewModel.setPassword(it) },
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 70.dp)
                    .height(70.dp),
                shape = RoundedCornerShape(20.dp),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, color = black),
                placeholder = {
                    Text(text = "Contraseña", fontSize = 25.sp, fontFamily = aladinFont, color = PurpleGrey80)
                }, colors = TextFieldDefaults.colors(focusedContainerColor = white,
                    unfocusedContainerColor = white,
                    disabledContainerColor = white,
                    focusedIndicatorColor = mostaza)
            )
            Button(
                onClick = { /*TODO*/ },
                Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(horizontal = 20.dp),
                colors = ButtonColors(
                    contentColor = black,
                    containerColor = mostaza,
                    disabledContainerColor = white,
                    disabledContentColor = black
                )
            ) {
                Text(
                    text = "Iniciar Sesión",
                    fontFamily = aladinFont,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "¿Has olvidado tu contraseña?",
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable { }
                    .padding(top = 20.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = aladinFont,
                textDecoration = TextDecoration.Underline,
                color = white
            )
        }
    }
}