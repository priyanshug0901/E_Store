package com.android.e_mart.adapters

import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.e_mart.databinding.ListItemAdapterBinding
import com.android.e_mart.model.Products
import com.bumptech.glide.Glide
import javax.inject.Inject

class HomeItemAdapter @Inject constructor() :
    RecyclerView.Adapter<HomeItemAdapter.HomeItemAdapterViewHolder>() {

   inner class HomeItemAdapterViewHolder(homeItemAdapterBinding: ListItemAdapterBinding) :
        RecyclerView.ViewHolder(homeItemAdapterBinding.root) {
        val binding: ListItemAdapterBinding = homeItemAdapterBinding
       fun setClickListener(){
           binding.root.setOnClickListener{

           }
       }
    }

    private var onClickListener: OnClickListener? = null
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
            Glide.with(this).load(product.images?.get(0)).into(holder.binding.itemImageView)
            holder.binding.product = product
            holder.setClickListener()
        }
    }

    private fun setClickListener(homeItemAdapterBinding: ListItemAdapterBinding, product : Products ) {

       homeItemAdapterBinding.listItemAdapterContainer.setOnClickListener {
            onClickListener?.onClick(product)
        }
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    interface OnClickListener {
        fun onClick(products: Products)
    }
}