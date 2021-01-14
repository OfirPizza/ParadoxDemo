package com.test.paradoxdemo.ui.productsList

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.test.paradoxdemo.R
import com.test.paradoxdemo.customUi.BaseProductsFragment
import com.test.paradoxdemo.models.ProductItemUiModel
import com.test.paradoxdemo.ui.product.DetailedProductFragment
import com.test.paradoxdemo.ui.productsList.adapter.ProductsAdapter
import kotlinx.android.synthetic.main.fragment_products.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsFragment : BaseProductsFragment(R.layout.fragment_products) {

    private val viewModel: ProductsFragmentViewModel by viewModel()
    private val adapter =  ProductsAdapter().apply {
        onSelectedItem = {this@ProductsFragment.onItemSelected(it)}
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
        products_recycler_view.adapter = adapter
        products_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getProductList()
                }
            }
        })
    }


    private fun initViewModel() {
        viewModel.apply {
            productsListLiveData.observe(viewLifecycleOwner, { this@ProductsFragment.updateAdapter(it) })
            getProductList()
        }
    }

    private fun updateAdapter(productsList: List<ProductItemUiModel>) {
        adapter.updateData(productsList)
        adapter.notifyDataSetChanged()
    }


    private fun onItemSelected(item: ProductItemUiModel) {
        val detailedProductFragment = DetailedProductFragment(item)
        detailedProductFragment.show(requireActivity().supportFragmentManager,detailedProductFragment::class.java.simpleName)
    }


    override fun onSearchProduct(str: String) {
        adapter.clearData()
        adapter.notifyDataSetChanged()
        viewModel.searchProductByName(str)
    }

}