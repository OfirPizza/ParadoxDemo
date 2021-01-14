package com.test.paradoxdemo.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.test.paradoxdemo.R
import com.test.paradoxdemo.customUi.BaseProductsFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {


    private val viewModel: MainFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.apply {
            fragmentLiveData.observe(viewLifecycleOwner, { showFragment(it) })
        }
    }

    private fun showFragment(fragment: BaseProductsFragment) {

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment, fragment::class.java.simpleName).commit()
    }

    private fun initViews() {
        initBottomNavigation()
    }

    private fun initBottomNavigation() {
        bottom_navigation_view.onNavigationClick = { viewModel.onNavigationClick(it) }
    }

}