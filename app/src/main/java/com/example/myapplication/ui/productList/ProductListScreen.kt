package com.example.myapplication.ui.productList

import ProductListViewModel
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.data.remote.response.Product
import com.google.gson.Gson
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductListScreen(
    navController: NavController,
    viewModel: ProductListViewModel
) {
    val products = viewModel.products.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.value.size) { item ->

            val product = products.value[item]

            ProductCard(product) {
                val json = Gson().toJson(product)
                val encoded = Uri.encode(json)
                navController.navigate("detail/$encoded")
            }
        }
    }
}


@Composable
fun ProductCard(product: Product, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed = interactionSource.collectIsPressedAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(true, 8.dp,Color.Black),
                onClick = onClick
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            GlideImage(
                imageModel = { product.image },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp), // height as per spec
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop // center crop
                ),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5)) // #F5F5F5 placeholder
                    )
                },
                failure = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                    )
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            AssistChip(
                onClick = {},
                label = { Text(product.category, fontSize = 10.sp) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color(0xFFE5E1FA),
                    labelColor = Color(0xFF6A32E6)
                )
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = product.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.height(6.dp))

            Row{
                Text(text = "⭐", fontSize = 14.sp)
                Text(
                    text = "${product.rating.rate} (${product.rating.count})",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = "$${product.price}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF6A32E6)
            )
        }
    }
}