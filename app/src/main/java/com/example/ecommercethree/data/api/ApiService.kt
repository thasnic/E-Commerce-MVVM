package com.example.ecommercethree.data.api

import com.example.ecommercethree.data.dto.Categories
import com.example.ecommercethree.data.dto.CategoriesItem
import com.example.ecommercethree.data.dto.Products
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProductsListFromApi(): Products
    @GET("products/categories")
    suspend fun getAllCategoriesListFromApi(): List<CategoriesItem>
    @GET("products/category/{categoryName}")
    suspend fun getProductsListByCategoryNameFromApi(@Path("categoryName") categoryName: String): Products
}