package com.example.ecommercethree.ui.screens.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.ecommercethree.common.ScreenState
import com.example.ecommercethree.data.api.ApiService
import com.example.ecommercethree.data.dto.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val apiService: ApiService,
    private val savedStateHandle: SavedStateHandle,
):ViewModel() {
    private val _product = MutableLiveData<ScreenState<Product>>()
    val product: LiveData<ScreenState<Product>> get() = _product

    init {
        getProduct()
    }
    fun getProduct(){
    savedStateHandle.get<Int>("productId")?.let { productId ->
        GlobalScope.launch {

            val result = apiService.getSingleProductByIdFromApi(productId)
//            Log.d("TAG", "getProduct: aaaaaaaa"+result)
            _product.postValue(ScreenState.Success(result))

        }
    }


//        GlobalScope.launch {
////            Log.d("TAG", "getProduct: ")
//            val result = apiService.getSingleProductByIdFromApi(productid)
////            Log.d("TAG", "getProduct: aaaaaaaa"+result)
//            _product.postValue(ScreenState.Success(result))
//        }
    }
}