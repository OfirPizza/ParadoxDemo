package com.test.paradoxdemo.ui.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.test.paradoxdemo.R
import com.test.paradoxdemo.models.ProductItemUiModel
import kotlinx.android.synthetic.main.list_product_item.view.*

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    private var data = mutableListOf<ProductItemUiModel>()
    var onSelectedItem: ((ProductItemUiModel) -> Unit)? = null

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_product_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: List<ProductItemUiModel>) {
        data.addAll(newData)
    }

    fun clearData() {
        data.clear()
    }

    inner class ProductsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            item: ProductItemUiModel
        ) {
            itemView.product_name.text = item.name
            itemView.product_price.text = item.price.toString()
            itemView.product_description.text = item.description
            Glide.with(itemView).load(item.imageUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.product_img)
            itemView.setOnClickListener {
                onSelectedItem?.invoke(item)

            }
        }
    }
}