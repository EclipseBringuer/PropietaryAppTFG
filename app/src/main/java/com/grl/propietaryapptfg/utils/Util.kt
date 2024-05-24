package com.grl.propietaryapptfg.utils

import android.icu.text.DecimalFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.grl.propietaryapptfg.R

class Util {
    companion object {
        @Composable
        fun loadFontFamilyFromAssets(): FontFamily {
            return FontFamily(Font(R.font.aladin_regular))
        }

        fun formatDouble(value: Double):String {
            val decimalFormat = DecimalFormat("0.#")
            return decimalFormat.format(value)
        }

        fun getImageResourceById(id: Int): Int {
            return when (id) {
                100 -> R.drawable.lata_cocacola
                102 -> R.drawable.cocacola_grande
                103 -> R.drawable.agua_pequena
                104 -> R.drawable.botella_agua_grande
                109 -> R.drawable.durum_mixto
                110 -> R.drawable.durum_queso
                111 -> R.drawable.durum_solo_carne
                112 -> R.drawable.durum_falafel
                113 -> R.drawable.kebab1
                114 -> R.drawable.maxi
                115 -> R.drawable.kebab_falafel
                116 -> R.drawable.kebab_patatas
                117 -> R.drawable.kebab_solo_carne
                118 -> R.drawable.hamburguesa_pollo
                119 -> R.drawable.hamburguesa
                120 -> R.drawable.lahmacun
                121 -> R.drawable.llahmacun_solo_carne
                122 -> R.drawable.menu_kebab
                123 -> R.drawable.menu_kebab_solo_carne
                124 -> R.drawable.menu_durum
                125 -> R.drawable.mesu_durum_solo_carne
                126 -> R.drawable.menu_hamburguesa
                127 -> R.drawable.racion_patatas
                128 -> R.drawable.racion_alitas_pollo
                129 -> R.drawable.racion_nuggets_pollo
                130 -> R.drawable.ensalada_kebab
                131 -> R.drawable.ensalada_pollo_kebab
                132 -> R.drawable.racion_falafel
                133 -> R.drawable.aros_de_cebolla
                134 -> R.drawable.patatas_bravas
                135 -> R.drawable.sprite_grande
                else -> throw IllegalArgumentException("Invalid ID")
            }
        }
    }
}