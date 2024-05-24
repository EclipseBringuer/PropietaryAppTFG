package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.grl.propietaryapptfg.ui.theme.black
import com.grl.propietaryapptfg.ui.theme.blackSoft
import com.grl.propietaryapptfg.ui.theme.darkRed
import com.grl.propietaryapptfg.ui.theme.granate
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.ui.theme.mostazaSoft
import com.grl.propietaryapptfg.ui.theme.white
import com.grl.propietaryapptfg.utils.Util

@Composable
fun ConfirmationDialog(onClick: () -> Unit, title: String, text: String) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    Dialog(onDismissRequest = { }) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(granate, RoundedCornerShape(20.dp))
        ) {
            val (titleText, description, bConfirm, divider) = createRefs()
            Text(
                text = title,
                color = mostaza,
                fontWeight = FontWeight.Bold,
                fontFamily = aladinFont,
                fontSize = 50.sp,
                style = TextStyle(lineHeight = 50.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(top = 10.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
            )
            HorizontalDivider(
                Modifier
                    .constrainAs(divider) {
                        top.linkTo(titleText.bottom)
                        bottom.linkTo(description.top)
                    }
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                color = mostaza,
                thickness = 3.dp
            )
            Text(
                text = text,
                color = white,
                fontFamily = aladinFont,
                fontSize = 30.sp,
                style = TextStyle(lineHeight = 35.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(titleText.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(bConfirm.top)
                }
            )
            Button(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .constrainAs(bConfirm) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                onClick = onClick,
                shape = RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 20.dp,
                    topStart = 0.dp,
                    topEnd = 0.dp
                ),
                colors = ButtonColors(
                    contentColor = black,
                    containerColor = mostaza,
                    disabledContainerColor = mostazaSoft,
                    disabledContentColor = blackSoft
                )
            ) {
                Text(
                    text = "Aceptar",
                    color = black,
                    fontFamily = aladinFont,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}

@Composable
fun ConfirmationDialogWithNegative(
    onPositive: () -> Unit,
    title: String,
    text: String,
    onNegative: () -> Unit
) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    Dialog(onDismissRequest = { }) {
        ConstraintLayout(
            Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(granate, RoundedCornerShape(20.dp))
        ) {
            val (titleText, description, bConfirm, bDenegate, divider) = createRefs()
            Text(
                text = title,
                color = mostaza,
                fontFamily = aladinFont,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                style = TextStyle(lineHeight = 51.sp),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 10.dp)
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    }
            )
            HorizontalDivider(
                Modifier
                    .constrainAs(divider) {
                        top.linkTo(titleText.bottom)
                        bottom.linkTo(description.top)
                    }
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 40.dp),
                color = mostaza,
                thickness = 3.dp
            )
            Text(
                text = text,
                color = white,
                fontFamily = aladinFont,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(lineHeight = 40.sp),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .constrainAs(description) {
                        top.linkTo(titleText.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        bottom.linkTo(bConfirm.top)
                    }
            )
            Button(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .constrainAs(bConfirm) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(bDenegate.start)
                        width = Dimension.fillToConstraints
                    },
                onClick = onPositive,
                shape = RoundedCornerShape(
                    bottomStart = 20.dp,
                    bottomEnd = 0.dp,
                    topEnd = 0.dp,
                    topStart = 0.dp
                ),
                colors = ButtonColors(
                    contentColor = black,
                    containerColor = mostaza,
                    disabledContainerColor = mostazaSoft,
                    disabledContentColor = blackSoft
                )
            ) {
                Text(
                    text = "Aceptar",
                    color = black,
                    fontFamily = aladinFont,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                modifier = Modifier
                    .height(70.dp)
                    .fillMaxWidth()
                    .constrainAs(bDenegate) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(bConfirm.end)
                        width = Dimension.fillToConstraints
                    },
                shape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 20.dp,
                    topStart = 0.dp,
                    topEnd = 0.dp
                ),
                onClick = onNegative,
                colors = ButtonColors(
                    contentColor = black,
                    containerColor = darkRed,
                    disabledContainerColor = mostazaSoft,
                    disabledContentColor = blackSoft
                )
            ) {
                Text(
                    text = "Cancelar",
                    color = black,
                    fontFamily = aladinFont,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}