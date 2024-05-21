package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.grl.propietaryapptfg.R

@Composable
fun LogoApp(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_bueno),
        contentDescription = "Imagen de la app",
        modifier = modifier
            .size(60.dp)
            .clip(shape = RoundedCornerShape(15.dp))
    )
}