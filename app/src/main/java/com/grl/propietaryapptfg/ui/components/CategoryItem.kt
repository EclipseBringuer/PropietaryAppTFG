package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.utils.Util

@Composable
fun CategoryItem(name: String, onClick: () -> Unit, isSelected: Boolean, modifier: Modifier) {
    ConstraintLayout(modifier = modifier
        .fillMaxHeight()
        .clickable { onClick() }) {
        val (title, bar) = createRefs()
        Text(
            modifier = Modifier
                .constrainAs(title) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                },
            text = name,
            textAlign = TextAlign.Center,
            color = mostaza,
            fontFamily = Util.loadFontFamilyFromAssets(),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold
        )
        if (isSelected) {
            HorizontalDivider(
                color = mostaza,
                thickness = 5.dp,
                modifier = Modifier.constrainAs(bar) { bottom.linkTo(parent.bottom) })
        }
    }
}