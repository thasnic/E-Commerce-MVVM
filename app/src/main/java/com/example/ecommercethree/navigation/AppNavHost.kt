package com.example.ecommercethree.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ecommercethree.ui.screens.auth.SignInRoute
import com.example.ecommercethree.ui.screens.auth.SignUpRoute
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

        composable(SignIn.route) {
            SignInRoute(
                onGoSignUpButtonClicked = {
                    navController.navigate(SignUp.route)
                },
                navigateToHomeScreen = {
//                    navController.navigate(Home.route)
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

    }
}