package com.example.ecommercethree.ui.screens.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ecommercethree.common.ScreenState
import com.example.ecommercethree.data.api.ApiService
import com.example.ecommercethree.data.dto.CategoriesItem
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
    private val _categories = MutableLiveData<ScreenState<List<CategoriesItem>>>()
    val categories: LiveData<ScreenState<List<CategoriesItem>>> get() = _categories

    init {
        getAllProducts()
        getAllCategory()
//        searchProduct("abc")
    }
    fun getAllProducts(){
        _products.postValue(ScreenState.Loading)
        GlobalScope.launch {
//            _products.postValue(ScreenState.Loading)
            val result = apiService.getProductsListFromApi()
//            Log.d("TAGqqqqqqqq", "getAllProducts: "+result.toString())
            if (result!= null){
//                val product: Products = result
                _products.postValue(ScreenState.Success(result.products))
            }
//            _products.postValue("sfsdf")
        }
    }
    fun getAllCategory(){
        GlobalScope.launch {
            val result = apiService.getAllCategoriesListFromApi()
//            Log.d("TAG", "getAllCategory: "+result.toString())
            _categories.postValue(ScreenState.Success(result))
        }
    }
    fun getProductsByCategory(categoryName: String){
        _products.postValue(ScreenState.Loading)
        GlobalScope.launch{
            val result = apiService.getProductsListByCategoryNameFromApi(categoryName)
//            Log.d("TAG", "getProductsByCategory:zzzzz "+result)

            _products.postValue(ScreenState.Success(result.products))
        }
    }
    fun searchProduct(query: String){
//        _products.postValue(ScreenState.Loading)
        GlobalScope.launch {
            Log.d("TAGSearch", "searchProduct: ")
            val result = apiService.getProductsListBySearchFromApi(query)
            Log.d("TAGSearch", "searchProduct: "+result)
            _products.postValue(ScreenState.Success(result.products))
        }
    }

}