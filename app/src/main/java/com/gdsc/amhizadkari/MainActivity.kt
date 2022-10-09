package com.gdsc.amhizadkari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.gdsc.amhizadkari.aboutus.AboutUsScreen
import com.gdsc.amhizadkari.main.MainScreen
import com.gdsc.amhizadkari.pastevents.PastEventScreen
import com.gdsc.amhizadkari.ui.theme.AppTheme
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.upcoming.UpcomingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
               Navigation()
            }
        }
    }
}


@Composable
fun Navigation() {

    val items = listOf(
        Routes.Home,
        Routes.PastEvents,
        Routes.UpcomingEvents,
        Routes.AboutUs
    )

    val navController = rememberNavController()
    Scaffold(
        topBar = {
                 TopAppBar(
                     elevation = 0.dp,
                     backgroundColor = MaterialTheme.colors.background,
                     contentColor = MaterialTheme.colors.onBackground
                 ) {
                     Row(
                         verticalAlignment = Alignment.CenterVertically,
                         horizontalArrangement = Arrangement.Start,
                         modifier = Modifier.padding(13.dp)
                     ) {
                         Text(
                             text = "Amhi Zadkari",
                             fontSize = 26.sp,
                             fontFamily = Poppins,
                             fontWeight = FontWeight.Medium,
                             color = MaterialTheme.colors.onBackground
                         )
                     }
                 }
        },


        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground,
                elevation = 20.dp
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach {item ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = item.icon, contentDescription = null) },
                        label = { Text(text = item.label, fontSize = 10.sp)},
                        selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = Routes.Home.route, Modifier.padding(innerPadding)) {
            composable(Routes.Home.route) { MainScreen(navController = navController) }
            composable(Routes.PastEvents.route) { PastEventScreen(navController) }
            composable(Routes.UpcomingEvents.route) { UpcomingScreen(navController = navController) }
            composable(Routes.AboutUs.route) { AboutUsScreen(navController) }
        }
    }
}