package com.android.e_mart.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.e_mart.dao.ProductTable
import com.android.e_mart.databinding.CartItemAdapterBinding
import com.android.e_mart.model.Products
import com.bumptech.glide.Glide

class CartItemAdapter(private val onProductClicked: (ProductTable) -> Unit, private val onCrossClicked: (ProductTable) -> Unit) :
    Adapter<CartItemAdapter.CartItemViewHolder>() {

    inner class CartItemViewHolder(val binding: CartItemAdapterBinding) : ViewHolder(binding.root) {
        fun setOnClickListener(products: ProductTable) {
            binding.root.setOnClickListener {
                onProductClicked(products)
            }
        }
    }

    var differCallback = object : DiffUtil.ItemCallback<ProductTable>() {
        override fun areItemsTheSame(
            oldItem: ProductTable,
            newItem: ProductTable
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductTable, newItem: ProductTable): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = CartItemViewHolder(
            CartItemAdapterBinding.inflate(
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

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        val product = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(product.thumbnail).into(holder.binding.productImage)
            holder.binding.product = product
            holder.setOnClickListener(product)
            holder.binding.crossButton.setOnClickListener{
               onCrossClicked(product)
            }
        }
    }

}