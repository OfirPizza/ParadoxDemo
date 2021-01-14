package com.test.paradoxdemo.customUi

import androidx.fragment.app.Fragment

abstract class BaseProductsFragment(id: Int) : Fragment(id) {
    abstract fun onSearchProduct(str: String)
    abstract fun onFilterProduct(id: Int)
}