package com.test.paradoxdemo.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.paradoxdemo.models.ProductItemUiModel
import com.test.paradoxdemo.repository.favorite.FavoriteRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailedProductFragmentViewModel(private val favoriteRepo: FavoriteRepo) : ViewModel() {


    private val isFavoriteMutableLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData: LiveData<Boolean> = isFavoriteMutableLiveData


    fun isProductFavorite(id: Int) {
        CoroutineScope(IO).launch {
            val isFavorite = favoriteRepo.isItemFavorite(id)
            postIsFavorite(isFavorite)
        }
    }

    fun toggleFavoriteState(item: ProductItemUiModel) {
        CoroutineScope(IO).launch {
            var isFavorite =  favoriteRepo.isItemFavorite(item.id)
            if (isFavorite){
                isFavorite=  !favoriteRepo.removeFavoriteItem(item)
            }else{
                isFavorite= favoriteRepo.addFavoriteItem(item)
            }
            postIsFavorite(isFavorite)
        }
    }

    private fun postIsFavorite(favorite: Boolean) {
        isFavoriteMutableLiveData.postValue(favorite)
    }

}