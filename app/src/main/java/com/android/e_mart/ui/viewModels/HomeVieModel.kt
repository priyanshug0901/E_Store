package com.android.e_mart.ui.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.e_mart.model.ProductsViewData
import com.android.e_mart.repository.ProductsRepository
import com.android.e_mart.repository.SearchRepository
import com.android.e_mart.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVieModel @Inject constructor(
    private val repository: ProductsRepository,
    private val searchRepository: SearchRepository
) : ViewModel(){
    private val _productLiveData : MutableLiveData<Resource<ProductsViewData>> = MutableLiveData()
    val productLiveData : LiveData<Resource<ProductsViewData>>
    get() = _productLiveData

    init{
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch{
            _productLiveData.postValue(Resource.Loading())
            val response = repository.getProducts()
            _productLiveData.postValue(handleRepoResponse(response))
        }
    }

    fun getSearchResults(searchQuery: String) {
        viewModelScope.launch {
            _productLiveData.postValue(Resource.Loading())
            val response = searchRepository.searchProduct(searchQuery)
            _productLiveData.postValue(handleRepoResponse(response))
        }
    }

    fun handleRepoResponse(response: ProductsViewData): Resource<ProductsViewData> {
        if(response.message == "ok"){
            return Resource.Success(response)
        }
        return Resource.Error("No products found")
    }
}