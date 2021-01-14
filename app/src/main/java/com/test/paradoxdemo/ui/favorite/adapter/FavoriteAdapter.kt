package com.test.paradoxdemo.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.paradoxdemo.R
import com.test.paradoxdemo.models.ProductItemUiModel
import kotlinx.android.synthetic.main.list_favorite_item.view.*

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private var data: List<ProductItemUiModel> = mutableListOf()
    var onSelectedItem: ((ProductItemUiModel) -> Unit)? = null

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_favorite_item, parent, false))
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(newData: List<ProductItemUiModel>) {
        data = newData
    }

    inner class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            item: ProductItemUiModel
        ) {
            itemView.favorite_name.text = item.name
            Glide.with(itemView).load(item.imageUrl).into(itemView.favorite_img)
            itemView.setOnClickListener {
                onSelectedItem?.invoke(item)
            }
        }
    }
}