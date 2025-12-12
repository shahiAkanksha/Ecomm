package com.example.myapplication.ui.productDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.data.remote.response.Product
import com.example.myapplication.ui.theme.DividerLightGrey
import com.google.gson.Gson
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductDetailScreen(productJson: String) {

    val product = remember { Gson().fromJson(productJson, Product::class.java) }

    val scrollState = rememberScrollState()

    Scaffold(
        bottomBar = {
            BuyNowButton(
                enabled = true,
                onClick = {  }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {

            GlideImage(
                imageModel = { product.image },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Inside
                ),
                loading = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFF5F5F5))
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

            Spacer(modifier = Modifier.height(8.dp))

            AssistChip(
                onClick = {},
                label = { Text(product.category, fontSize = 10.sp) },
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = Color(0xFFE5E1FA),
                    labelColor = Color(0xFF6A32E6)
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "⭐", fontSize = 14.sp)
                Text(
                    text = "${product.rating.rate} (${product.rating.count})",
                    fontSize = 13.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(
                color = DividerLightGrey, // from your theme Color.kt
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))

            Text(
                text = "Price: ₹${product.price}",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(Modifier.height(8.dp))
            HorizontalDivider(
                color = DividerLightGrey, // from your theme Color.kt
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Description".uppercase(),
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(Modifier.height(12.dp))

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun BuyNowButton(
    enabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE),
            contentColor = Color.White,
            disabledContainerColor = Color(0xFFBDBDBD),
            disabledContentColor = Color(0xFF757575)
        ),
        shape = RoundedCornerShape(4.dp),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),

        interactionSource = remember { MutableInteractionSource() }
    ) {
        Text(
            text = "Buy Now",
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

