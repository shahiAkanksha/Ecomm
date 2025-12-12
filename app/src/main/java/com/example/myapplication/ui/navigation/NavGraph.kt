package com.example.myapplication.ui.navigation

import ProductListViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.data.repository.ProductRepo
import com.example.myapplication.ui.productDetail.ProductDetailScreen
import com.example.myapplication.ui.productList.ProductListScreen
import com.example.myapplication.utils.ViewModelFactory

@Composable
fun NavGraph(repository: ProductRepo) {

    val navController = rememberNavController()
    val factory = ViewModelFactory(repository)

    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            val vm: ProductListViewModel = viewModel(factory = factory)
            ProductListScreen(navController, vm)
        }

        composable(
            "detail/{product}",
            arguments = listOf(
                navArgument("product") { type = NavType.StringType }
            )
        ) { entry ->
            val productJson = entry.arguments?.getString("product")!!
            ProductDetailScreen(productJson)
        }
    }
}


