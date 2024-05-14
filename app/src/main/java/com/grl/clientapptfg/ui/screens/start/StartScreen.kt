package com.grl.clientapptfg.ui.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grl.clientapptfg.R
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun StartScreen(startViewModel: StartViewModel) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(500.dp)
                    .paint(
                        painter = painterResource(id = R.drawable.app_logo),
                        contentScale = ContentScale.Crop,
                    )
            ) {
                Text(
                    text = "Pa-Ke-ba",
                    fontFamily = aladinFont,
                    fontSize = 100.sp,
                    color = mostaza,
                    modifier = Modifier.align(
                        Alignment.Center
                    ),
                    style = TextStyle(
                        shadow = Shadow(
                            color = black,
                            blurRadius = 10f,
                            offset = Offset(10.0f, 30.0f)
                        )
                    )
                )
            }
            Text(
                text = "¿Quienes Somos?",
                fontFamily = aladinFont,
                color = mostaza,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(granate)
            ) {
                Text(
                    text = "¡Sumérgete en el delicioso mundo de los sabores auténticos con Kebab Paradise! Desde nuestro humilde inicio en 2005, nos hemos esforzado por ofrecer a nuestros clientes una experiencia gastronómica inigualable que combina la frescura de los ingredientes, la sazón tradicional y un ambiente acogedor que te hace sentir como en casa.",
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 20.dp)
                        .align(Alignment.Center),
                    color = white,
                    fontFamily = aladinFont,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(lineHeight = 45.sp)
                )
            }
        }
    }
}