package com.example.ecommercethree.data.api

import com.example.ecommercethree.data.dto.Products
import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProductsListFromApi(): Products
}