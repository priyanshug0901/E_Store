package com.android.e_mart.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.e_mart.dao.ProductTable
import com.android.e_mart.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository): ViewModel() {

    fun removeItemFromCart(sneakerTable: ProductTable) {
        cartRepository.removeItemFromCart(sneakerTable)
    }

    fun getSavedItems(): LiveData<List<ProductTable>> {
        return cartRepository.getAllItem()
    }

    fun getTotalCardPrice(): Int{
        return cartRepository.getTotalCardPrice()
    }

    fun deleteAllItemsFromCart() {
        viewModelScope.launch {
            cartRepository.deleteAllItemsFromCart()
        }
    }
}