package com.example.ecommercethree.navigation

interface AppDestinations {
    val route: String
}
object Home : AppDestinations {
    override val route = "home"
}
object Splash : AppDestinations {
    override val route = "splash"
}
object SignUp : AppDestinations {
    override val route = "signUp"
}