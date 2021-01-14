package com.test.paradoxdemo.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.paradoxdemo.models.PaginationUiModel
import com.test.paradoxdemo.models.ProductItemUiModel
import com.test.paradoxdemo.models.ProductsUiModel
import com.test.paradoxdemo.repository.products.ProductsRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ProductsFragmentViewModel(private val productsRepo: ProductsRepo) : ViewModel() {

    private var currentPage = 0
    private var maxPage = 1
    private var currentNameSearch = ""

    private val productsListMutableLiveData = MutableLiveData<List<ProductItemUiModel>>()
    val productsListLiveData: LiveData<List<ProductItemUiModel>> = productsListMutableLiveData


    fun getProductList() {
        if (currentPage >= maxPage) {
            return
        }
        CoroutineScope(IO).launch {
            val productList = productsRepo.getProductList(currentPage++,currentNameSearch)
            setPagination(productList.pagination)
            postProductList(productList)
        }
    }

    fun searchProductByName(productName: String) {
        currentNameSearch = productName
        resetPagination()
        getProductList()
    }

    private fun resetPagination() {
        maxPage = 1
        currentPage = 0
    }


    private fun postProductList(productList: ProductsUiModel) {
        productsListMutableLiveData.postValue(productList.productsList)
    }

    private fun setPagination(pagination: PaginationUiModel) {
        maxPage = pagination.totalPages
        currentPage = pagination.currentPage
    }


}