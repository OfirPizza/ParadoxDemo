package com.test.paradoxdemo.ui.main.di

import com.test.paradoxdemo.ui.main.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val mainFragmentModule = module {
    viewModel { MainFragmentViewModel() }
}
