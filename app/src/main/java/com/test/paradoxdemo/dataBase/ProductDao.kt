package com.test.paradoxdemo.dataBase

import androidx.room.*
import com.test.paradoxdemo.dataBase.entity.FavoriteProductEntity

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(favoriteProductItem: FavoriteProductEntity)

    @Delete
    suspend fun removeFavoriteProduct(favoriteProductItem: FavoriteProductEntity)

    @Query("SELECT * FROM favorite_products WHERE id = :id")
    suspend fun getProductById(id: Int): FavoriteProductEntity?

    @Query("SELECT * FROM favorite_products WHERE name LIKE '%' || :search || '%'")
    suspend fun getProductByName(search: String?):List<FavoriteProductEntity>

    @Transaction
    suspend fun isProductFavorite(id: Int): Boolean {
        return getProductById(id) != null
    }

    @Query("SELECT * FROM favorite_products")
    suspend fun getFavoriteProducts(): List<FavoriteProductEntity>
}