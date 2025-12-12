package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.ApiService

class ProductRepo(private val api: ApiService) {
    suspend fun getProducts() = api.getProducts()

}