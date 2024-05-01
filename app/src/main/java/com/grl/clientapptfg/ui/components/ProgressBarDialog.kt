package com.grl.clientapptfg.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.grl.clientapptfg.ui.theme.mostaza

@Composable
fun ProgressBarDialog() {
    Dialog(onDismissRequest = { }) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                Modifier
                    .align(Alignment.Center)
                    .size(120.dp),
                color = mostaza,
                strokeWidth = 10.dp
            )
        }
    }
}