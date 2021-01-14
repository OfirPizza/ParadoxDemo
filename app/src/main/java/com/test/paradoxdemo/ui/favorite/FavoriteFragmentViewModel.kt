package com.test.paradoxdemo.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.paradoxdemo.models.ProductItemUiModel
import com.test.paradoxdemo.repository.favorite.FavoriteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FavoriteFragmentViewModel(private val favoriteRepo: FavoriteRepo) : ViewModel() {

    private val favoriteListMutableLiveData = MutableLiveData<List<ProductItemUiModel>>()
    val favoriteListLiveData: LiveData<List<ProductItemUiModel>> = favoriteListMutableLiveData


    fun getFavoriteList() {
        CoroutineScope(IO).launch {
            val favoriteList = favoriteRepo.getFavoriteList()
            postFavoriteList(favoriteList)
        }
    }


    fun searchProductByName(productName: String) {
        if (productName.isEmpty()) {
            getFavoriteList()
            return
        }

        CoroutineScope(IO).launch {
            val favoriteList = favoriteRepo.getFavoriteListByName(productName)
            postFavoriteList(favoriteList)
        }
    }


    private fun postFavoriteList(favoriteList: List<ProductItemUiModel>) {
        favoriteListMutableLiveData.postValue(favoriteList)
    }

}