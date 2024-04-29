package com.grl.clientapptfg.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.grl.clientapptfg.R

class Util {
    companion object {
        @Composable
        fun loadFontFamilyFromAssets(): FontFamily {
            return FontFamily(Font(R.font.aladin_regular))
        }
    }
}