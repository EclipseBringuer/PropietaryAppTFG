package com.grl.clientapptfg.ui.screens.user_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.clientapptfg.core.UserSession
import com.grl.clientapptfg.ui.components.ConfirmationDialogWithNegative
import com.grl.clientapptfg.ui.screens.profile.ProfileViewModel
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.blackSoft
import com.grl.clientapptfg.ui.theme.darkRed
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.mostazaSoft
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util

@Composable
fun UserDetailsScreen(
    userDetailsViewModel: UserDetailsViewModel,
    profileViewModel: ProfileViewModel
) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    val closeSession = userDetailsViewModel.closeSession.observeAsState(initial = false)
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        val (title, body, divider, button) = createRefs()
        val topGuide = createGuidelineFromTop(0.03f)
        val startGuide = createGuidelineFromStart(0.05f)
        val endGuide = createGuidelineFromEnd(0.05f)

        if (closeSession.value) {
            ConfirmationDialogWithNegative(
                onPositive = {
                    userDetailsViewModel.changeCloseSession(false)
                    UserSession.setUser(null)
                    UserSession.cleanList()
                    profileViewModel.setScreenState(1)
                },
                title = "¿Estas seguro de cerrar sesión?",
                text = "Pulsa aceptar para salir",
                onNegative = { userDetailsViewModel.changeCloseSession(false) })
        }

        Text(
            text = "Detalles de mi cuenta",
            Modifier
                .padding(bottom = 10.dp)
                .constrainAs(title) {
                    top.linkTo(topGuide)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                },
            fontWeight = FontWeight.Bold,
            fontSize = 50.sp,
            fontFamily = aladinFont,
            color = white
        )
        HorizontalDivider(
            Modifier
                .constrainAs(divider) {
                    top.linkTo(title.bottom)
                }
                .padding(horizontal = 20.dp)
                .padding(bottom = 40.dp),
            color = mostaza,
            thickness = 3.dp
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .constrainAs(body) {
                    top.linkTo(divider.bottom)
                    start.linkTo(startGuide)
                    end.linkTo(endGuide)
                }) {
            Row {
                DetailsText(text = "- Nombre: ", color = mostaza, modifier = Modifier.padding(bottom = 30.dp))
                DetailsText(text = UserSession.getUser()!!.name, color = white, Modifier.padding(bottom = 30.dp))
            }
            Row {
                DetailsText(text = "- Teléfono: ", color = mostaza, Modifier.padding(bottom = 30.dp))
                DetailsText(text = UserSession.getUser()!!.phone, color = white, Modifier.padding(bottom = 30.dp))
            }
            DetailsText(text = "- Correo: ", color = mostaza)
            DetailsText(text = UserSession.getUser()!!.gmail, color = white, Modifier.padding(bottom = 30.dp, start = 30.dp))
            DetailsText(text = "- Dirección: ", color = mostaza)
            DetailsText(text = UserSession.getUser()!!.address, color = white, Modifier.padding(bottom = 25.dp, start = 30.dp))
        }
        Button(
            onClick = {
                userDetailsViewModel.changeCloseSession(true)
            },
            Modifier
                .fillMaxWidth()
                .height(115.dp)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .constrainAs(button) {
                    bottom.linkTo(parent.bottom)
                },
            colors = ButtonColors(
                contentColor = black,
                containerColor = darkRed,
                disabledContainerColor = mostazaSoft,
                disabledContentColor = blackSoft
            ),
        ) {
            Text(
                text = "Cerrar Sesión",
                fontFamily = Util.loadFontFamilyFromAssets(),
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DetailsText(text: String, color: Color, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontFamily = Util.loadFontFamilyFromAssets(),
        fontSize = 32.sp,
        color = color,
        modifier = modifier
    )
}