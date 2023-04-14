package com.android.e_mart.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.e_mart.R
import com.android.e_mart.adapters.CartItemAdapter
import com.android.e_mart.dao.ProductTable
import com.android.e_mart.databinding.CartFragmentBinding
import com.android.e_mart.model.Products
import com.android.e_mart.ui.viewModels.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    lateinit var binding: CartFragmentBinding
    lateinit var cartAdapter: CartItemAdapter
    val cartViewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.cart_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCartAdapter()
        setUpRecyclerView()
        updateOrderDetails()
    }

    fun setNavigationCLickListener(products: ProductTable) {
        val bundle = Bundle()
        val product = Products(
            products.brand,
            products.category,
            products.description,
            products.discountPercentage,
            products.id,
            null,
            products.price,
            products.rating,
            products.stock,
            products.thumbnail,
            products.title
        )
        bundle.putSerializable("products", product)
        findNavController().navigate(R.id.action_cartFragment_to_itemDetailFragment, bundle)
    }

    private fun setUpRecyclerView() {
        binding.cartRecyclerView.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setCartAdapter() {
        cartAdapter = CartItemAdapter(::setNavigationCLickListener, ::setCrossButtonClick)
        cartViewModel.getSavedItems().observe(viewLifecycleOwner) {
            cartAdapter.differ.submitList(it)
        }
    }

    private fun setCrossButtonClick(product: ProductTable) {
        cartViewModel.removeItemFromCart(product)
        updateOrderDetails()
    }

    fun updateOrderDetails() {
        var totalPrice = cartViewModel.getTotalCardPrice()
        binding.totalPrices = totalPrice
    }

}