package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.ui.theme.red
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun PersonalizedDivider(modifier: Modifier, text: String, color: Boolean) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(
            Modifier
                .weight(1f)
                .padding(start = 20.dp), color = mostaza
        )
        Text(
            text = text,
            Modifier.padding(horizontal = 15.dp),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Util.loadFontFamilyFromAssets(),
            textAlign = TextAlign.Center,
            color = if (color) {
                red
            } else {
                white
            }
        )
        HorizontalDivider(
            Modifier
                .weight(1f)
                .padding(end = 20.dp), color = mostaza
        )
    }
}