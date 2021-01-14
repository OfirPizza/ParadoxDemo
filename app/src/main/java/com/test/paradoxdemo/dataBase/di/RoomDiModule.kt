package com.test.paradoxdemo.dataBase.di

import androidx.room.Room
import com.test.paradoxdemo.dataBase.RoomProductsDb
import org.koin.dsl.module

private const val DATABASE = "favorite_products"

val roomModule = module {
    single { Room.databaseBuilder(get(), RoomProductsDb::class.java, DATABASE).build() }
    single { get<RoomProductsDb>().roomProductDao() }
}