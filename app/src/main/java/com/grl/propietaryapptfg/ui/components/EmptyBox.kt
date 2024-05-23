package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.utils.Util

@Composable
fun EmptyBox(modifier: Modifier) {
    Box(modifier = modifier) {
        Text(
            text = "No hay pedidos en este estado todavía",
            modifier = Modifier.align(Alignment.Center),
            fontFamily = Util.loadFontFamilyFromAssets(),
            fontSize = 35.sp,
            textAlign = TextAlign.Center,
            color = mostaza
        )
    }
}