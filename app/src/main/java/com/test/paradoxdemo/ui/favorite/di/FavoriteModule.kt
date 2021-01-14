package com.test.paradoxdemo.ui.favorite.di

import com.test.paradoxdemo.ui.favorite.FavoriteFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favoriteFragmentModule = module {
    viewModel { FavoriteFragmentViewModel(get()) }
}
