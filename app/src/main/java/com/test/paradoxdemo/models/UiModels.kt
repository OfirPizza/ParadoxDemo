package com.test.paradoxdemo.models

data class ProductsUiModel(
    val pagination: PaginationUiModel,
    val productsList: List<ProductItemUiModel>
)

data class ProductItemUiModel(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Float,
)

data class PaginationUiModel(
    val totalPages: Int,
    val currentPage: Int
)