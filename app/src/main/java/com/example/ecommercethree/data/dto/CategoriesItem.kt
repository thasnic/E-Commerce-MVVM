package com.example.ecommercethree.data.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CategoriesItem(
    @Json(name = "name")
    val name: String,
    @Json(name = "slug")
    val slug: String,
    @Json(name = "url")
    val url: String
)