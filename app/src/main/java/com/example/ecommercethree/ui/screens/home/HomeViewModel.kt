package com.example.ecommercethree.ui.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommercethree.common.ScreenState
import com.example.ecommercethree.data.api.ApiService
import com.example.ecommercethree.data.dto.Product
import com.example.ecommercethree.data.dto.Products
import com.example.ecommercethree.ui.uiData.ProductUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val apiService: ApiService
):ViewModel() {
//    private val _products = MutableLiveData<String>()
//    val products: LiveData<String> = _products
    private val _products = MutableLiveData<ScreenState<List<Product>>>()
    val products: LiveData<ScreenState<List<Product>>> get() = _products

//    init {
//        getAllProducts()
//    }
    fun getAllProducts(){

        GlobalScope.launch {
            val result = apiService.getProductsListFromApi()
//            Log.d("TAGqqqqqqqq", "getAllProducts: "+result.toString())
            if (result!= null){
//                val product: Products = result
                _products.postValue(ScreenState.Success(result.products))
//                _products.postValue(ScreenState.Error("errorrrrrrrrrr"))
//                _products.postValue(ScreenState.Loading)
//                _products.postValue(ScreenState.Success(List))

            }

//            _products.postValue("sfsdf")
        }
    }

}