package com.test.paradoxdemo.repository.favorite

import com.test.paradoxdemo.dataBase.ProductDao
import com.test.paradoxdemo.dataBase.entity.FavoriteProductEntity
import com.test.paradoxdemo.models.ProductItemUiModel

class FavoriteRepoImpl(private val productDao: ProductDao) : FavoriteRepo {


    override suspend fun getFavoriteList(): List<ProductItemUiModel> {
        return toProductUiModel(productDao.getFavoriteProducts())
    }

    override suspend fun getFavoriteListByName(name: String): List<ProductItemUiModel> {
        return toProductUiModel(productDao.getProductByName(name))
    }

    override suspend fun addFavoriteItem(item: ProductItemUiModel): Boolean {
        productDao.insertOrUpdate(toFavoriteModel(item))
        return isItemFavorite(item.id)
    }

    private fun toFavoriteModel(item: ProductItemUiModel): FavoriteProductEntity {
        return FavoriteProductEntity(
            id = item.id,
            name = item.name,
            imageUrl = item.imageUrl,
            description = item.description,
            price = item.price
        )
    }

    override suspend fun removeFavoriteItem(item: ProductItemUiModel): Boolean {
        productDao.removeFavoriteProduct(toFavoriteModel(item))
        return !isItemFavorite(item.id)
    }

    override suspend fun isItemFavorite(productId: Int): Boolean {
        return productDao.isProductFavorite(productId)
    }


    private fun toProductUiModel(favoriteProducts: List<FavoriteProductEntity>): List<ProductItemUiModel> {
        return favoriteProducts.map {
            ProductItemUiModel(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                description = it.description,
                price = it.price
            )
        }
    }

}