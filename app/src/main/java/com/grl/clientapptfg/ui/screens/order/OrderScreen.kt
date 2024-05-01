package com.grl.clientapptfg.ui.screens.order

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.clientapptfg.ui.theme.granate

@Composable
fun OrderScreen(orderViewModel: OrderViewModel) {
    ConstraintLayout(Modifier.fillMaxSize().background(granate)) {
        val (texto, cabecera, cuerpo) = createRefs()
        Text(
            text = "Esto es la pantalla de carrito",
            Modifier
                .constrainAs(texto) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
    }
}