package com.example.myapplication.data.remote

import com.example.myapplication.data.remote.response.Product
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}