package com.android.e_mart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.e_mart.databinding.ListItemAdapterBinding
import com.android.e_mart.model.Products
import com.bumptech.glide.Glide
import javax.inject.Inject

class HomeItemAdapter(private val onProductClicked: (Products) -> Unit) :
    RecyclerView.Adapter<HomeItemAdapter.HomeItemAdapterViewHolder>() {

    inner class HomeItemAdapterViewHolder(homeItemAdapterBinding: ListItemAdapterBinding) :
        RecyclerView.ViewHolder(homeItemAdapterBinding.root) {
        val binding: ListItemAdapterBinding = homeItemAdapterBinding
        fun setClickListener(product: Products) {
            binding.root.setOnClickListener {
                onProductClicked(product)
            }
        }
    }


    var differCallback = object : DiffUtil.ItemCallback<Products>() {
        override fun areItemsTheSame(
            oldItem: Products,
            newItem: Products
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeItemAdapterViewHolder {
        val view = HomeItemAdapterViewHolder(
            ListItemAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return view
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: HomeItemAdapterViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(product.images?.get(0)).into(holder.binding.productImageView)
            holder.binding.product = product
            holder.setClickListener(product)
        }
    }
}