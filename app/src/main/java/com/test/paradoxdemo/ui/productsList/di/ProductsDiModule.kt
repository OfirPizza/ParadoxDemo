package com.test.paradoxdemo.ui.productsList.di

import com.test.paradoxdemo.repository.products.ProductsRepo
import com.test.paradoxdemo.repository.products.ProductsRepoImpl
import com.test.paradoxdemo.ui.productsList.ProductsFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val productsFragmentModule = module {
    factory<ProductsRepo> { ProductsRepoImpl(get()) }
    viewModel { ProductsFragmentViewModel(get()) }
}
