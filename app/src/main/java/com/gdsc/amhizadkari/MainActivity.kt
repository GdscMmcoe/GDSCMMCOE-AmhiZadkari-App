package com.gdsc.amhizadkari

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gdsc.amhizadkari.contactus.ContactUsScreen
import com.gdsc.amhizadkari.event.EventScreen
import com.gdsc.amhizadkari.splash.SplashScreen
import com.gdsc.amhizadkari.ui.theme.AppTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Navigation()
            }

            var backPressTime = 0L
            val context = LocalContext.current
            BackHandler {
                val time = System.currentTimeMillis()
                if(time - backPressTime > 3000){
                    Toast.makeText(context,"Press back again to exit",Toast.LENGTH_SHORT).show()
                    backPressTime = time
                }
                else {
                    exitProcess(0)
                }
            }
        }
    }
}


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route
    ){
        composable(Routes.SplashScreen.route){
            SplashScreen(navController)
        }
        composable(Routes.BottomNav.route){
            BottomNav(navController)
        }
        composable(Routes.ContactUs.route){
            ContactUsScreen(navController)
        }
        composable(
            Routes.EventScreen.route + "/{event_name}/{event_content}",
            arguments = listOf(
                navArgument("event_name"){ type = NavType.StringType },
                navArgument("event_content"){ type = NavType.StringType }
            )
        ){
            EventScreen(
                title = it.arguments?.getString("event_name") ?: "",
                content = it.arguments?.getString("event_content") ?: "",
                navController
            )
        }
    }

}