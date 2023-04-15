package com.android.e_mart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.PrimaryKey
import com.android.e_mart.R
import com.android.e_mart.dao.ProductTable
import com.android.e_mart.databinding.ItemDetailFragmentBinding
import com.android.e_mart.model.Products
import com.android.e_mart.ui.viewModels.ProductDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    lateinit var binding : ItemDetailFragmentBinding
    private val viewModel by viewModels<ProductDetailViewModel>()
    val args : ItemDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.item_detail_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.products
        binding.product = product
        binding.rating = product.rating.toInt()
        Glide.with(this).load(product.thumbnail).into(binding.itemScrollImageView)

        handleNavigationToCart(product)
    }

    fun handleNavigationToCart(product: Products) {
        binding.addToCartCTA.setOnClickListener {
            saveProductToCart(product)
            findNavController().navigate(R.id.action_itemDetailFragment_to_cartFragment)
        }
    }

    fun saveProductToCart(product: Products) {
        val productTable = ProductTable(product.id,
            product.brand,
            product.category,
            product.description,
            product.discountPercentage,
            product.price,
            product.rating,
            product.stock,
            product.thumbnail,product.title)
        viewModel.addProductToCart(productTable)
    }
}

//@PrimaryKey
//val id: Int,
//val brand: String?,
//val category: String?,
//val description: String?,
//val discountPercentage: Double,
//val price: Int,
//val rating: Double,
//val stock: Int,
//val thumbnail: String?,
//val title: String?