package com.grl.clientapptfg.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.grl.clientapptfg.R
import java.security.MessageDigest

class Util {
    companion object {
        @Composable
        fun loadFontFamilyFromAssets(): FontFamily {
            return FontFamily(Font(R.font.aladin_regular))
        }

        fun hashPassword(password: String): String {
            val bytes = MessageDigest.getInstance("SHA-256").digest(password.toByteArray())
            return bytes.joinToString("") { "%02x".format(it) }
        }

        fun verifyPassword(password: String, hashedPassword: String): Boolean {
            val newPasswordHash = hashPassword(password)
            return newPasswordHash == hashedPassword
        }
    }
}