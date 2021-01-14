package com.test.paradoxdemo.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.paradoxdemo.dataBase.entity.FavoriteProductEntity

@Database(
    entities = [FavoriteProductEntity::class],
    version = 1,
    exportSchema = false
)

abstract class RoomProductsDb : RoomDatabase() {
    abstract fun roomProductDao(): ProductDao
}