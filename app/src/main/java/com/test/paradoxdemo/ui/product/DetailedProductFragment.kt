package com.test.paradoxdemo.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import com.test.paradoxdemo.R
import com.test.paradoxdemo.models.ProductItemUiModel
import kotlinx.android.synthetic.main.fragment_product_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedProductFragment(private val productItem : ProductItemUiModel) : DialogFragment() {

    var onFavoriteItemChange: ((ProductItemUiModel) -> Unit)? = null
    private val viewModel : DetailedProductFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_details, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.apply {
            isFavoriteLiveData.observe(viewLifecycleOwner,{
                updateFavoriteUi(it)
                dispatchItemChanged()
            })
            isProductFavorite(productItem.id)
        }
    }

    private fun updateFavoriteUi(isFavorite: Boolean) {
        if (isFavorite){
            setFavoriteText(resources.getString(R.string.remove_as_favorite))
        }else{
            setFavoriteText(resources.getString(R.string.add_as_favorite))
        }
    }

    private fun setFavoriteText(string: String) {
        product_favorite.text = string
    }

    private fun initViews() {
        setProductName()
        setProductImage()
        setFavorite()
        setProductDescription()

    }

    private fun setProductDescription() {
        product_description.text = productItem.description

    }

    private fun setFavorite() {
        product_favorite.setOnClickListener {
            viewModel.toggleFavoriteState(productItem)
        }
    }

    private fun setProductImage() {
        Glide.with(this).load(productItem.imageUrl).placeholder(resources.getDrawable(R.drawable.ic_no_pic)).into(product_img)
    }

    private fun setProductName() {
        product_name.text = productItem.name
    }

    private fun dispatchItemChanged() {
        onFavoriteItemChange?.invoke(productItem)
    }
}