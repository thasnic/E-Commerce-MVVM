package com.example.ecommercethree.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommercethree.ui.screens.auth.SignInRoute
import com.example.ecommercethree.ui.screens.auth.SignUpRoute
import com.example.ecommercethree.ui.screens.cart.CartRoute
import com.example.ecommercethree.ui.screens.favorite.FavoriteRoute
import com.example.ecommercethree.ui.screens.home.HomeRoute
import com.example.ecommercethree.ui.screens.profile.ProfileRoute
import com.example.ecommercethree.ui.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
//    onBadgeCountChange: (Int) -> Unit
){
    NavHost(navController = navController, startDestination = Splash.route, modifier = modifier) {
        composable(Splash.route) {
            SplashScreen(

                navigateToHomeScreen = {
                    navController.navigate(SignIn.route)
                },
            )
        }

        composable(Home.route) {
            HomeRoute(
                onProductClicked = {
//                    val route = "${ProductDetail.route}/${it.id}"
//                    navController.navigate(route = route)
                },
            )
        }

        composable(SignIn.route) {
            SignInRoute(
                onGoSignUpButtonClicked = {
                    navController.navigate(SignUp.route)
                },
                navigateToHomeScreen = {
                    navController.navigate(Home.route)
                },
            )
        }

        composable(SignUp.route) {
            SignUpRoute(
                navigateToSignInScreen = {
                    navController.navigate(SignIn.route)
                },
            )
        }
//        composable(Cart.route){
//            CartRoute()
//        }
        composable(Cart.route){
            CartRoute(
                onClickedBuyNowButton = {
                    navController.navigate(Payment.route)
                },
//                onCartClicked = {
//                    val route = "${ProductDetail.route}/${it.productId}"
//                    navController.navigate(route = route)
//                },
//                onBadgeCountChange = onBadgeCountChange,
            )
        }
        composable(Favorite.route){
            FavoriteRoute(
                onProductClicked = {
//                    val route = "${ProductDetail.route}/${it.productId}"
//                    navController.navigate(route = route)
                },
            )
        }
        composable(Profile.route) {
            ProfileRoute(
                logout = {
                    navController.navigate(SignIn.route) {
                        popUpTo(SignIn.route) {
                            inclusive = true
                        }
                    }
                },
            )
        }

    }
}