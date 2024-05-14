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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grl.clientapptfg.core.Constants
import com.grl.clientapptfg.data.models.ProductModel
import com.grl.clientapptfg.ui.components.ConfirmationDialog
import com.grl.clientapptfg.ui.components.OrderProductDialog
import com.grl.clientapptfg.ui.components.ProgressBarDialog
import com.grl.clientapptfg.ui.screens.tabs_menu.TabsMenuViewModel
import com.grl.clientapptfg.ui.theme.black
import com.grl.clientapptfg.ui.theme.granate
import com.grl.clientapptfg.ui.theme.mostaza
import com.grl.clientapptfg.utils.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(menuViewModel: MenuViewModel, tabsMenuViewModel: TabsMenuViewModel) {
    val isLoading = menuViewModel.isLoading.observeAsState(initial = true)
    val isVisible = menuViewModel.isVisible.observeAsState(initial = false)
    val isFirstTime = menuViewModel.isFirstTime.observeAsState(initial = true)
    val badLogged = menuViewModel.badLogged.observeAsState(initial = false)
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = screenWidth / 3
    val productSelected = menuViewModel.productSelected.observeAsState()
    val products =
        menuViewModel.products.observeAsState(
            mutableListOf()
        )
    val categories = menuViewModel.categories.observeAsState(
        initial = Constants.Companion.Category.getListOfCategories()
    )
    val selectedIndex = menuViewModel.selectedIndex.observeAsState(initial = 0)
    val categoriesState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    if (isFirstTime.value) {
        menuViewModel.getProducts()
        menuViewModel.changeFirstTime(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(granate)
    ) {
        if (isLoading.value) {
            ProgressBarDialog()
        }
        if (isVisible.value) {
            OrderProductDialog(
                onDismiss = { menuViewModel.setIsVisible(false) },
                product = productSelected.value!!,
                context = LocalContext.current,
                { menuViewModel.changeBadLogged(true) },
                tabsMenuViewModel
            )
        }
        if (badLogged.value) {
            ConfirmationDialog(
                onClick = { menuViewModel.changeBadLogged(false) },
                title = "¡No has iniciado sesión!",
                text = "Debes iniciar sesión antes de añadir productos al pedido, pulsa aceptar para volver"
            )
        }
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
                        modifier = Modifier.width(itemWidth),
                        painter = menuViewModel.getPhotoByCategory(category = category)
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
                ProductItem(product, menuViewModel)
            }
        }
    }
}


@Composable
fun CategoryItem(
    category: String,
    onClick: () -> Unit,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    painter: Painter
) {
    ConstraintLayout(modifier = modifier
        .fillMaxHeight()
        .clickable { onClick() }) {
        val (image, text, bar) = createRefs()
        val endGuide = createGuidelineFromBottom(0.1f)
        val topGuide = createGuidelineFromTop(0.03f)
        Image(
            painter = painter,
            contentDescription = "Imagen de prueba",
            modifier = Modifier
                .size(90.dp)
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
fun ProductItem(product: ProductModel, menuViewModel: MenuViewModel) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 15.dp),
        colors = CardDefaults.cardColors(containerColor = black),
        shape = CardDefaults.shape,
        modifier = Modifier
            .clickable {
                menuViewModel.setActualProduct(product)
                menuViewModel.setIsVisible(true)
            }
            .fillMaxWidth()
            .height(270.dp)
            .padding(horizontal = 12.dp, vertical = 10.dp)
    ) {
        ConstraintLayout(Modifier.fillMaxSize()) {
            val (image, name, price) = createRefs()
            val topGuide = createGuidelineFromTop(0.05f)
            Image(
                painter = if (product.photo == 0) {
                    menuViewModel.getPhotoByCategory(category = product.category)
                } else {
                    painterResource(id = product.photo)
                },
                contentDescription = "Imagen del producto",
                Modifier
                    .size(110.dp)
                    .constrainAs(image) {
                        top.linkTo(topGuide)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = product.name,
                color = mostaza,
                fontFamily = Util.loadFontFamilyFromAssets(),
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                style = TextStyle(lineHeight = 30.sp),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(top = 0.dp, start = 9.dp, end = 9.dp)
                    .constrainAs(name) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "${Util.formatDouble(product.price)}€",
                color = mostaza,
                fontFamily = Util.loadFontFamilyFromAssets(),
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .constrainAs(price) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
            )
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