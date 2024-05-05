package com.grl.clientapptfg.ui.screens.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.clientapptfg.R
import com.grl.clientapptfg.data.models.ProductModel
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.ui.theme.white
import com.grl.clientapptfg.utils.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(menuViewModel: MenuViewModel) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth / 3
    menuViewModel.getProducts()
    val products =
        menuViewModel.products.observeAsState(
            listOf(
                ProductModel(
                    1,
                    "nada",
                    4.5,
                    byteArrayOf(),
                    "",
                    ""
                )
            )
        )
    val categories = menuViewModel.categories.observeAsState(
        initial = menuViewModel.getCategories()
    )
    val selectedIndex = menuViewModel.selectedIndex.observeAsState(initial = 0)
    menuViewModel.filterByCategory(categories.value[selectedIndex.value])
    val categoriesState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        Surface(
            shadowElevation = 10.dp,
            modifier = Modifier.padding(bottom = 8.dp),
            color = granate
        ) {
            LazyRow(
                state = categoriesState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(categories.value) { index, category ->
                    CategoryItem(
                        category = category,
                        onClick = {
                            menuViewModel.changeSelectedIndex(index)
                            menuViewModel.filterByCategory(category)
                            coroutineScope.launch {
                                categoriesState.animateScrollAndCentralizeItem(index, this)
                            }
                        }, isSelected = index == selectedIndex.value,
                        modifier = Modifier.width(itemWidth)
                    )
                }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .background(granate),
        ) {
            items(products.value) { product ->
                ProductItem(product)
            }
        }
    }
}


@Composable
fun CategoryItem(
    category: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(modifier = modifier
        .fillMaxHeight()
        .clickable { onClick() }) {
        val (image, text, bar) = createRefs()
        val endGuide = createGuidelineFromBottom(0.1f)
        val topGuide = createGuidelineFromTop(0.03f)
        Image(
            painter = painterResource(id = R.drawable.tortilla),
            contentDescription = "Imagen de prueba",
            modifier = Modifier
                .size(95.dp)
                .constrainAs(image) {
                    bottom.linkTo(text.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(topGuide)
                })
        Text(
            modifier = Modifier
                .constrainAs(text) {
                    bottom.linkTo(endGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, text = category, textAlign = TextAlign.Center,
            color = mostaza,
            fontFamily = Util.loadFontFamilyFromAssets(),
            fontSize = 25.sp,
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

@Composable
fun ProductItem(product: ProductModel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        colors = CardDefaults.cardColors(containerColor = white),
        shape = CardDefaults.shape,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        ConstraintLayout {

        }
    }
}

fun LazyListState.animateScrollAndCentralizeItem(index: Int, scope: CoroutineScope) {
    val itemInfo = this.layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
    scope.launch {
        if (itemInfo != null) {
            val center = this@animateScrollAndCentralizeItem.layoutInfo.viewportEndOffset / 2
            val childCenter = itemInfo.offset + itemInfo.size / 2
            this@animateScrollAndCentralizeItem.animateScrollBy((childCenter - center).toFloat())
        } else {
            this@animateScrollAndCentralizeItem.animateScrollToItem(index)
        }
    }
}