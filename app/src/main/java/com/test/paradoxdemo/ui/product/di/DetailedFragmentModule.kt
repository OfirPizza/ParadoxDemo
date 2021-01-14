package com.test.paradoxdemo.ui.product.di

import com.test.paradoxdemo.repository.favorite.FavoriteRepo
import com.test.paradoxdemo.repository.favorite.FavoriteRepoImpl
import com.test.paradoxdemo.ui.product.DetailedProductFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val deatiledFragmentModule = module {
    factory<FavoriteRepo> { FavoriteRepoImpl(get()) }
    viewModel { DetailedProductFragmentViewModel(get()) }
}
