package com.test.paradoxdemo.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.paradoxdemo.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {


    private val viewModel: MainFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
    }

    private fun initViews() {
    }
}