package com.test.paradoxdemo.repository.products

import com.test.paradoxdemo.models.ProductsUiModel

interface ProductsRepo {
    suspend fun getProductList(pageNumber: Int,name: String?) : ProductsUiModel
}