package com.test.paradoxdemo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.paradoxdemo.customUi.BaseProductsFragment
import com.test.paradoxdemo.customUi.BottomNavigation
import com.test.paradoxdemo.ui.products.ProductsFragment

class MainFragmentViewModel : ViewModel() {


    private val fragmentMutableLiveData = MutableLiveData<BaseProductsFragment>()
    val fragmentLiveData: LiveData<BaseProductsFragment> = fragmentMutableLiveData


    fun onNavigationClick(navigationType: BottomNavigation.BottomNavigationType) {
        when (navigationType) {
            BottomNavigation.BottomNavigationType.PRODUCTS -> {
                postShowFragment(ProductsFragment())
            }
            BottomNavigation.BottomNavigationType.FAVORITE -> {
            }
        }
    }

    private fun postShowFragment(fragment: BaseProductsFragment) {
        fragmentMutableLiveData.postValue(fragment)
    }
}