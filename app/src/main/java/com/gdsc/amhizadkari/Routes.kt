package com.gdsc.amhizadkari

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Routes(val route: String, val icon: ImageVector?,val label:String?) {
    object Home : Routes("home", Icons.Default.Home,"Home")
    object AboutUs : Routes("aboutUs", Icons.Default.Groups,"About Us")
    object Donate : Routes("donateUs",Icons.Default.Money,"Donate Us")

    object ContactUs : Routes("ContactUs",null,null)
    object SplashScreen: Routes("splash",null,null)
    object BottomNav: Routes("splash",null,null)
}