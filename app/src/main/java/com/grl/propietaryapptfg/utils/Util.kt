package com.grl.propietaryapptfg.utils

import android.icu.text.DecimalFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.grl.propietaryapptfg.R
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

        fun formatDouble(value: Double):String {
            val decimalFormat = DecimalFormat("0.#")
            return decimalFormat.format(value)
        }

        /*Por si ocurre un fallo con las fotos
        fun getPhotos():List<Images>{
            return listOf(
                Images(100, R.drawable.lata_cocacola),
                Images(102, R.drawable.cocacola_grande),
                Images(103, R.drawable.agua_pequena),
                Images(104, R.drawable.botella_agua_grande),
                Images(109, R.drawable.durum_mixto),
                Images(110, R.drawable.durum_queso),
                Images(111, R.drawable.durum_solo_carne),
                Images(112, R.drawable.durum_falafel),
                Images(113, R.drawable.kebab1),
                Images(114, R.drawable.maxi),
                Images(115, R.drawable.kebab_falafel),
                Images(116, R.drawable.kebab_patatas),
                Images(117, R.drawable.kebab_solo_carne),
                Images(118, R.drawable.hamburguesa_pollo),
                Images(119, R.drawable.hamburguesa),
                Images(120, R.drawable.lahmacun),
                Images(121, R.drawable.llahmacun_solo_carne),
                Images(122, R.drawable.menu_kebab),
                Images(123, R.drawable.menu_kebab_solo_carne),
                Images(124, R.drawable.menu_durum),
                Images(125, R.drawable.mesu_durum_solo_carne),
                Images(126, R.drawable.menu_hamburguesa),
                Images(127, R.drawable.racion_patatas),
                Images(128, R.drawable.racion_alitas_pollo),
                Images(129, R.drawable.racion_nuggets_pollo),
                Images(130, R.drawable.ensalada_kebab),
                Images(131, R.drawable.ensalada_pollo_kebab),
                Images(132, R.drawable.racion_falafel),
                Images(133, R.drawable.aros_de_cebolla),
                Images(134, R.drawable.patatas_bravas),
                Images(135, R.drawable.sprite_grande),
                )
        }*/
    }
}