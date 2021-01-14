package com.test.paradoxdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.paradoxdemo.customUi.BaseProductsFragment
import com.test.paradoxdemo.customUi.BottomNavigation
import com.test.paradoxdemo.ui.favorite.FavoriteFragment
import com.test.paradoxdemo.ui.productsList.ProductsFragment

class MainFragmentViewModel : ViewModel() {


    private val fragmentMutableLiveData = MutableLiveData<BaseProductsFragment>()
    val fragmentLiveData: LiveData<BaseProductsFragment> = fragmentMutableLiveData

    private val titleMutableLiveData = MutableLiveData<String>()
    val titleLiveData: LiveData<String> = titleMutableLiveData

    init {
        onNavigationClick(BottomNavigation.BottomNavigationType.PRODUCTS)
    }

    fun onNavigationClick(navigationType: BottomNavigation.BottomNavigationType) {
        when (navigationType) {
            BottomNavigation.BottomNavigationType.PRODUCTS -> {
                postShowFragment(ProductsFragment())
            }
            BottomNavigation.BottomNavigationType.FAVORITE -> {
                postShowFragment(FavoriteFragment())
            }
        }
        postTitleName(navigationType.name)
    }

    private fun postTitleName(name: String) {
        titleMutableLiveData.postValue(name)
    }

    private fun postShowFragment(fragment: BaseProductsFragment) {
        fragmentMutableLiveData.postValue(fragment)
    }
}