package com.example.myapplication.ui.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun ProductDetailScreen(
    productJson: String,
    navController: NavController,
) {

    val product = remember { Gson().fromJson(productJson, Product::class.java) }
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopBar(onBack = {
                navController.popBackStack()
            })
        },
        bottomBar = {
            AddToCartButton(onClick = {})
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .background(Color.White)
        ) {

            // 🖼 Image Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
                    .background(Color(0xFFF3F3F3)),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    imageModel = { product.image },
                    modifier = Modifier.size(150.dp),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Fit
                    )
                )
            }

            // 📌 White Card Section
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color.White, shape = RoundedCornerShape(20.dp))
                    .padding(20.dp)
            ) {

                // Category Tag
                Box(
                    modifier = Modifier
                        .background(Color(0x1A7B14F3), RoundedCornerShape(6.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = product.category.uppercase(),
                        color = Color(0xFF7B14F3),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(14.dp))

                // Title
                Text(
                    text = product.title,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(Modifier.height(12.dp))

                // ⭐ Rating
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "⭐", fontSize = 16.sp)
                    Text(
                        text = " ${product.rating.rate} (${product.rating.count} reviews)",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

                Spacer(Modifier.height(16.dp))

                Divider(color = Color(0xFFE0E0E0))

                Spacer(Modifier.height(16.dp))

                // Price
                Text(
                    text = "$${product.price}",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF7B14F3)
                )

                Spacer(Modifier.height(16.dp))

                Divider(color = Color(0xFFE0E0E0))

                Spacer(Modifier.height(16.dp))

                // Description Title
                Text(
                    text = "DESCRIPTION",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )

                Spacer(Modifier.height(10.dp))

                // Description
                Text(
                    text = product.description,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun TopBar(onBack: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF7B14F3))
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(26.dp)
                .clickable { onBack() }
        )

        Text(
            text = "Product Detail",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Composable
fun AddToCartButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF7B14F3), RoundedCornerShape(8.dp))
                .padding(vertical = 14.dp)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "ADD TO CART",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
