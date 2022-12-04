package com.gdsc.amhizadkari

import FaqScreen
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.gdsc.amhizadkari.contactus.ContactUsScreen
import com.gdsc.amhizadkari.event.EventScreen
import com.gdsc.amhizadkari.splash.SplashScreen
import com.gdsc.amhizadkari.terms.TandCScreen
import com.gdsc.amhizadkari.ui.theme.AppTheme
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins
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
            val keyPref = LocalContext.current.getSharedPreferences("authkey", Context.MODE_PRIVATE)
            var agree by remember { mutableStateOf(keyPref.getBoolean("agree",false)) }
            val editor = keyPref.edit()

            AppTheme {
                if(!agree){
                    AskAck(editor){
                        agree = true
                    }
                }

                else{
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

        composable(Routes.TandCScreen.route){
            TandCScreen(navController)
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

@Composable
fun AskAck(editor: SharedPreferences.Editor, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 40.dp)
        ){
            Text(
                text = stringResource(id = R.string.ack),
                fontSize = 24.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Text(
                text = stringResource(id = R.string.ackcontent),
                textAlign = TextAlign.Justify
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            Button(
                onClick = {
                editor.putBoolean("agree", true)
                editor.commit()
                onClick()
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(text = stringResource(R.string.agree_continue))
            }
        }

    }
}

