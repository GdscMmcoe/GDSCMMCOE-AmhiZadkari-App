package com.gdsc.amhizadkari

import FaqScreen
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.gdsc.amhizadkari.contactus.ContactUsScreen
import com.gdsc.amhizadkari.event.EventScreen
import com.gdsc.amhizadkari.splash.SplashScreen
import com.gdsc.amhizadkari.ui.theme.AppTheme
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.util.ReadData
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ReadData()
                Navigation()

                val green = MaterialTheme.colors.CardColor
                val systemUiController = rememberSystemUiController()
                DisposableEffect(key1 = systemUiController){
                    systemUiController.setSystemBarsColor(
                        color = green
                    )
                    onDispose {  }
                }

            }

            var backPressTime = 0L
            val context = LocalContext.current
            BackHandler {
                val time = System.currentTimeMillis()
                if(time - backPressTime > 3000){
                    Toast.makeText(context,getString(R.string.back_exit),Toast.LENGTH_SHORT).show()
                    backPressTime = time
                }
                else {
                    exitProcess(0)
                }
            }
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation() {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Routes.SplashScreen.route,
        enterTransition = {slideInHorizontally(initialOffsetX = {it})},
        exitTransition = {slideOutHorizontally(targetOffsetX = {it})}
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

        composable(Routes.FaqScreen.route){
            FaqScreen(navController)
        }


        composable(
            Routes.EventScreen.route + "/{event_id}",
            arguments = listOf(
                navArgument("event_id"){ type = NavType.IntType }
            )
        ){
            EventScreen(
                navController = navController,
                id = it.arguments?.getInt("event_id")?:0
            )
        }
    }

}