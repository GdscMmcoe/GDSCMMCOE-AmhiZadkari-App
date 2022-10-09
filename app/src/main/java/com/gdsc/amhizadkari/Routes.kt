package com.gdsc.amhizadkari

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Routes(val route: String, val icon: ImageVector,val label:String) {
    object Home : Routes("home", Icons.Default.Home,"Home")
    object PastEvents : Routes("pastEvents", Icons.Default.EventAvailable,"Past Events")
    object UpcomingEvents : Routes("upcomingEvents", Icons.Default.DateRange,"Future Events")
    object AboutUs : Routes("aboutUs", Icons.Default.Groups,"About Us")
}