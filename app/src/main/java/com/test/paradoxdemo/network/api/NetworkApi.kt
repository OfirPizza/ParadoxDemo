package com.test.paradoxdemo.network.api

import com.test.paradoxdemo.models.ProductsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface NetworkApi {

    @Headers("Content-Type: application/json")
    @GET("products")
    suspend fun getProducts(
        @Query("page") page: Int,
        @Query("name") name: String? = ""
    ): ProductsResponse
}