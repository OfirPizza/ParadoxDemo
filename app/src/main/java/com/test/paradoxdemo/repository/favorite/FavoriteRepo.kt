package com.test.paradoxdemo.repository.favorite

import com.test.paradoxdemo.models.ProductItemUiModel

interface FavoriteRepo {
    suspend fun getFavoriteList(): List<ProductItemUiModel>
    suspend fun getFavoriteListByName(name: String): List<ProductItemUiModel>
    suspend fun addFavoriteItem(item: ProductItemUiModel): Boolean
    suspend fun removeFavoriteItem(item: ProductItemUiModel): Boolean
    suspend fun isItemFavorite(productId: Int): Boolean
}