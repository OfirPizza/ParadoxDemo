package com.test.paradoxdemo.ui.favorite

import android.os.Bundle
import android.view.View
import com.test.paradoxdemo.R
import com.test.paradoxdemo.customUi.BaseProductsFragment
import com.test.paradoxdemo.models.ProductItemUiModel
import com.test.paradoxdemo.ui.favorite.adapter.FavoriteAdapter
import com.test.paradoxdemo.ui.product.DetailedProductFragment
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : BaseProductsFragment(R.layout.fragment_favorite) {

    private val viewModel: FavoriteFragmentViewModel by viewModel()
    private val adapter = FavoriteAdapter().apply {
        onSelectedItem = { this@FavoriteFragment.onItemSelected(it) }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }


    private fun initViews() {
        setRecyclerView()
    }

    private fun setRecyclerView() {
        favorite_recycler_view.adapter = adapter
    }


    private fun initViewModel() {
        viewModel.apply {
            favoriteListLiveData.observe(viewLifecycleOwner, { this@FavoriteFragment.updateAdapter(it) })
            getFavoriteList()
        }
    }

    private fun updateAdapter(productsList: List<ProductItemUiModel>) {
        adapter.updateData(productsList)
        adapter.notifyDataSetChanged()
    }


    private fun onItemSelected(item: ProductItemUiModel) {
        val detailedProductFragment = DetailedProductFragment(item).apply {
            onFavoriteItemChange = {viewModel.getFavoriteList()}
        }
        detailedProductFragment.show(requireActivity().supportFragmentManager,detailedProductFragment::class.java.simpleName)
    }


    override fun onSearchProduct(str: String) {
        viewModel.searchProductByName(str)
    }

}