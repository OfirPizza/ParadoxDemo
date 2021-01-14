package com.test.paradoxdemo.models

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("meta")
    val meta: PaginationResponse,
    @SerializedName("data")
    val productsList: List<ProductItemResponse>
)

data class PaginationResponse(
    @SerializedName("pages")
    val totalPages: Int,
    @SerializedName("page")
    val currentPage: Int
)

data class ProductItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("price")
    val price: Float,
    @SerializedName("discount_amount")
    val discountPrice: Float,
    @SerializedName("categories")
    val categories: List<CategoriesItemResponse>
)

data class CategoriesItemResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)
