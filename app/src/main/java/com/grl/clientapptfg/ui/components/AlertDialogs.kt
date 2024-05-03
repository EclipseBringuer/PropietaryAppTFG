package com.grl.clientapptfg.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.blackSoft
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.mostazaSoft
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util

@Composable
fun ConfirmationDialog(onClick: () -> Unit, title: String, text: String) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    AlertDialog(
        onDismissRequest = {
        },
        containerColor = granate,
        title = {
            Text(
                text = title,
                color = white,
                fontFamily = aladinFont,
                fontSize = 33.sp,
            )
        },
        text = {
            Text(
                text = text,
                color = mostaza,
                fontFamily = aladinFont,
                fontSize = 20.sp,
            )
        },
        confirmButton = {
            Button(
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                onClick = onClick,
                colors = ButtonColors(
                    contentColor = black,
                    containerColor = mostaza,
                    disabledContainerColor = mostazaSoft,
                    disabledContentColor = blackSoft
                )
            ) {
                Text(text = "Aceptar", color = black, fontFamily = aladinFont, fontSize = 20.sp)
            }
        }
    )
}