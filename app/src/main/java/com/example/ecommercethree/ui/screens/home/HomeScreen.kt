package com.example.ecommercethree.ui.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import com.example.ecommercethree.R
import com.example.ecommercethree.common.ScreenState
import com.example.ecommercethree.data.dto.CategoriesItem
import com.example.ecommercethree.data.dto.Product
import com.example.ecommercethree.ui.Loading
import com.example.ecommercethree.ui.uiData.ProductUiData
@Composable
fun HomeRoute(
    onProductClicked: (ProductUiData) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
//    viewModel.getAllProducts()
//    viewModel.getAllCategory()
//    viewModel.getProductsByCategory("beauty")
    val a = viewModel.products.observeForever{
        Log.d("TAGbbbbb==>", "HomeRoute: "+it)
    }
    val productState by viewModel.products.observeAsState(initial = ScreenState.Loading)
    val categoryState by viewModel.categories.observeAsState(initial = ScreenState.Loading)
    Log.d("TAG", "HomeRoute: Category"+categoryState)
    val onCategoryClicked = { category: String ->
        viewModel.getProductsByCategory(category)
        println("clickedaaaaaaaaaaaa"+category)

//        Log.d("TAG", "HomeRoute: clicked")
    }
    var searchQuery by remember { mutableStateOf("") }
    val onSearchTextChanged: (String) -> Unit = { newSearchQuery ->
        searchQuery = newSearchQuery
        if (newSearchQuery.isNotEmpty()) {
//            viewModel.searchProduct(newSearchQuery)
        }
    }
    HomeScreen(
        productState = productState,
        categoryState = categoryState,
        onProductClicked = onProductClicked,
        onCategoryClicked = onCategoryClicked,
        onSearchTextChanged = onSearchTextChanged,
        searchQuery = searchQuery,
    )
}

@Composable
fun HomeScreen(
    productState: ScreenState<List<Product>>?,
    categoryState: ScreenState<List<CategoriesItem>>,
    onProductClicked: (ProductUiData) -> Unit,
    onCategoryClicked: (String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    searchQuery: String,
) {
    Log.d("TAG", "HomeScreen: ")
    Log.d("TAG", "HomeScreenFirst: "+productState)
    Log.d("TAG", "HomeScreenSecond: "+categoryState)
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            productState is ScreenState.Success && categoryState is ScreenState.Success
                    -> {
                SuccessScreen(
                    productUiData = productState.uiData,
                    categoryUiData = categoryState.uiData,
                    onCategoryClicked = onCategoryClicked,
                    onProductClicked = onProductClicked,
                    onSearchTextChanged = onSearchTextChanged,
                    searchQuery = searchQuery,
                )
            }
            productState is ScreenState.Loading || categoryState is ScreenState.Loading -> {
                Loading()
            }

        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    productUiData: List<Product>,
    categoryUiData: List<CategoriesItem>,
    onProductClicked: (ProductUiData) -> Unit = {},
    onCategoryClicked: (String) -> Unit,
    onSearchTextChanged: (String) -> Unit,
    searchQuery: String,
){
    var active by remember { mutableStateOf(false) }
    var searchQueryState = searchQuery
    Column(modifier = modifier) {
        androidx.compose.material3.SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .padding(10.dp),
            query = searchQueryState,
            onQueryChange = onSearchTextChanged,
            onSearch = {
                active = false
            },
            active = active,
            onActiveChange = { active = it },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = null)
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (searchQueryState.isNotEmpty()) {
                                searchQueryState = ""
                                onSearchTextChanged("")
                            } else {
                                active = false
                            }
                        },
                        imageVector = Icons.Filled.Close,
                        contentDescription = null,
                    )
                }
            },
            placeholder = { Text(text = stringResource(id = R.string.search_hint)) },
        ) {
        }
        CategoryList(
            categories = categoryUiData,
            onCategoryClicked = onCategoryClicked,
        )

        ProductList(
            products = productUiData,
            onProductClicked = onProductClicked,
        )
    }
}
@Preview
@Composable
fun LoadingItemPreview() {
//    AppTheme {
//        Loading()
//    }
}

@Preview
@Composable
fun ErrorPreview() {
//    AppTheme {
//        Box {
//            Error("Unexpected Error")
//        }
//    }
}