package com.grl.propietaryapptfg.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.propietaryapptfg.data.models.ItemModel
import com.grl.propietaryapptfg.ui.theme.black
import com.grl.propietaryapptfg.ui.theme.mostaza
import com.grl.propietaryapptfg.utils.Util

@Composable
fun ItemCard(item: ItemModel) {
    val aladinFont = Util.loadFontFamilyFromAssets()
    Card(
        modifier = Modifier
            .size(260.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        border = BorderStroke(3.dp, mostaza),
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        colors = CardDefaults.cardColors(containerColor = black),
        shape = RoundedCornerShape(
            bottomStart = 40.dp,
            bottomEnd = 40.dp,
            topStart = 40.dp,
            topEnd = 40.dp
        )
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (image, itemName, amount) = createRefs()
            Image(
                painter = painterResource(id = Util.getImageResourceById(item.product.id)),
                contentDescription = "item image",
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 5.dp)
                    .size(120.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = item.product.name,
                fontFamily = aladinFont,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = TextStyle(lineHeight = 30.sp),
                color = mostaza,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .constrainAs(itemName) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "X ${item.amount}", fontFamily = aladinFont,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = mostaza,
                modifier = Modifier.constrainAs(amount) {
                    bottom.linkTo(parent.bottom)
                    top.linkTo(itemName.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}