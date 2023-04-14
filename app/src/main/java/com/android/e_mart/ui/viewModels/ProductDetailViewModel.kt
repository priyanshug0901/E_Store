package com.android.e_mart.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.e_mart.dao.ProductTable
import com.android.e_mart.repository.ProductDetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val productDetailRepository: ProductDetailRepository) : ViewModel(){

    fun addProductToCart(productTable: ProductTable): Boolean {
        viewModelScope.launch {
            productDetailRepository.insertProductInDb(productTable)
        }
        return true;
    }
}