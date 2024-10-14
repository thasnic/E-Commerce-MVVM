package com.example.ecommercethree

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecommercethree.navigation.AppNavHost
import com.example.ecommercethree.navigation.SignUp
import com.example.ecommercethree.navigation.Splash
import com.example.ecommercethree.ui.theme.EcommerceThreeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceThreeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
fun App(
    modifier: Modifier = Modifier,
//    appState: EcommerceAppState = rememberEcommerceAppState(),
//    badgeCount: Int,
//    onBadgeCountChange: (Int) -> Unit
){
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ){
//        if (!appState.isOnline) {
//            OfflineDialog(onRetry = appState::refreshOnline)
//        }

        val navController = rememberNavController()
        val bottomBarState = rememberSaveable { (mutableStateOf(false)) }
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        when (navBackStackEntry?.destination?.route) {
//            SignIn.route,
            SignUp.route, Splash.route -> bottomBarState.value = false
            else -> bottomBarState.value = true
        }

        Scaffold {
            paddingValues ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(paddingValues),
//                onBadgeCountChange = onBadgeCountChange,
            )
        }
    }
}

@Composable
fun rememberEcommerceAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current,
) = remember(navController, context){
    EcommerceAppState(navController, context)
}

class EcommerceAppState(
    val navController: NavHostController,
    private val context: Context,
){
//    var isOnline by mutableStateOf(checkIfOnline())
//        private set
//    fun refreshOnline() {
//        isOnline = checkIfOnline()
//    }


}