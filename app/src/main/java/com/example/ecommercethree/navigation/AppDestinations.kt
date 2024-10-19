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
object Cart : AppDestinations {
    override val route = "cart"
}

object Profile : AppDestinations {
    override val route = "profile"
}
object SignIn : AppDestinations {
    override val route = "signIn"
}

object SignUp : AppDestinations {
    override val route = "signUp"
}

object Favorite : AppDestinations {
    override val route = "favorite"
}

object Payment : AppDestinations {
    override val route = "payment"
}