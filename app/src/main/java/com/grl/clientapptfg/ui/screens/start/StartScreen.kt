package com.grl.clientapptfg.ui.screens.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun StartScreen() {
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
                        painter = painterResource(id = R.drawable.logo_bueno),
                        contentScale = ContentScale.Crop,
                    )
            ) {
                Text(
                    text = "Pa-Ke-Ba",
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
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 40.dp, bottom = 20.dp)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(granate)
            ) {
                Text(
                    text = "¡Sumérgete en el delicioso mundo de los sabores auténticos con Pa-Ke-Ba!\n\nDesde nuestro humilde inicio en 2005, nos hemos esforzado por ofrecer a nuestros clientes una experiencia gastronómica inigualable\n\nQue combina la frescura de los ingredientes, la sazón tradicional y un ambiente acogedor que te hace sentir como en casa.",
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
            Image(
                painter = painterResource(id = R.drawable.fondo_presentacion),
                contentDescription = "kebabs de fondo",
                Modifier
                    .padding(top = 30.dp)
                    .fillMaxSize()
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 20.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
            )
            Text(
                text = "¿Que nos hace especiales?",
                Modifier
                    .background(black)
                    .padding(top = 40.dp, bottom = 10.dp),
                style = TextStyle(lineHeight = 40.sp),
                fontFamily = aladinFont,
                color = mostaza,
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(black)
            ) {
                Text(
                    text = "En Pa-Ke-Ba, no solo servimos kebabs...\n\n¡Sino que creamos obras maestras culinarias!\n\nNuestro equipo de chefs expertos fusiona cuidadosamente las especias más exquisitas con los ingredientes frescos\n\nPara ofrecerte auténticos sabores que te transportarán a las calles de Oriente Medio con cada bocado.",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 40.dp)
                        .align(Alignment.Center),
                    color = white,
                    fontFamily = aladinFont,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(lineHeight = 45.sp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.delivery_fondo),
                contentDescription = "Paquete de fondo",
                Modifier
                    .height(275.dp)
                    .width(600.dp)
                    .background(black)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 20.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
            )
            Text(
                text = "¡Servicio a domicilio disponible!",
                Modifier
                    .background(granate)
                    .padding(top = 30.dp, bottom = 10.dp),
                style = TextStyle(lineHeight = 40.sp),
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
                    text = "¿Prefieres disfrutar de nuestros sabores en la comodidad de tu hogar?\n¡No hay problema!\n\nCon nuestro servicio a domicilio, puedes disfrutar de todos tus platos favoritos de Kebab Paradise donde quiera que estés.\n\nSolo regístrate en nuestra aplicación y realiza tu pedido, nosotros nos encargaremos del resto.",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 40.dp)
                        .align(Alignment.Center),
                    color = white,
                    fontFamily = aladinFont,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(lineHeight = 45.sp)
                )
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .background(black)
                    .clip(
                        RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    )
            ) {
                Text(
                    text = "¡Únete a la familia Pa-Ka-Ba hoy mismo!\nRegístrate en nuestra aplicación y comienza a disfrutar\n\nNúmero: +34   896  78  65  32",
                    modifier = Modifier
                        .padding(start = 10.dp, top = 50.dp, end = 10.dp, bottom = 40.dp)
                        .align(Alignment.Center),
                    color = mostaza,
                    fontFamily = aladinFont,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    style = TextStyle(lineHeight = 45.sp)
                )
            }
        }
    }
}