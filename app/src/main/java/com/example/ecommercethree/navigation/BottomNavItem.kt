package com.example.ecommercethree.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
){
    object Home : BottomNavItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = com.example.ecommercethree.navigation.Home.route,
    )

    object Cart : BottomNavItem(
        title = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = com.example.ecommercethree.navigation.Cart.route,
    )

    object Favorite : BottomNavItem(
        title = "Favorite",
        icon = Icons.Default.Favorite,
        route = com.example.ecommercethree.navigation.Favorite.route,
    )

    object Profile : BottomNavItem(
        title = "Profile",
        icon = Icons.Default.AccountCircle,
        route = com.example.ecommercethree.navigation.Profile.route,
    )
}