package com.test.paradoxdemo.repository.products

import com.test.paradoxdemo.models.*
import com.test.paradoxdemo.network.api.NetworkApi
import com.test.paradoxdemo.util.Result

class ProductsRepoImpl(private val service: NetworkApi) : ProductsRepo {

    override suspend fun getProductList(pageNumber: Int,name: String?): ProductsUiModel {
        val result = Result.build { service.getProducts(pageNumber,name) }
        return when (result) {
            is Result.Value -> {
                toUiProductList(result.value)
            }
            is Result.Error -> TODO()
        }
    }

    private fun toUiProductList(products: ProductsResponse): ProductsUiModel {
        return ProductsUiModel( toPaginationUiModel(products.meta),products.productsList.map { toUiProductItem(it) })
    }

    private fun toPaginationUiModel(paginationResponse: PaginationResponse): PaginationUiModel {
        return PaginationUiModel(totalPages =  paginationResponse.pagination.totalPages,currentPage = paginationResponse.pagination.currentPage)
    }

    private fun toUiProductItem(itemResponse: ProductItemResponse): ProductItemUiModel {
        return ProductItemUiModel(
            id = itemResponse.id,
            name = itemResponse.name,
            description = itemResponse.description,
            imageUrl = itemResponse.imageUrl,
            price = itemResponse.price
        )
    }


}