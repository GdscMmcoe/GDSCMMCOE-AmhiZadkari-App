package com.gdsc.amhizadkari

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gdsc.amhizadkari.ui.theme.AppTheme
import com.gdsc.amhizadkari.ui.theme.Poppins
import kotlinx.coroutines.delay
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme {
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ){
                    composable("splash"){
                        SplashScreen(navController)
                    }
                    composable("Main"){
                        Navigation()
                    }
                }
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
fun SplashScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.start_page),
            contentDescription = "Start Page",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            Spacer(modifier = Modifier.padding(180.dp))

            Text(
                text = "Amhi Zadkari",
                fontSize = 40.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(
                text = "Government Registered NGO",
                fontSize = 14.sp,
                fontFamily = Poppins,
                fontWeight = FontWeight.Bold,
                color = Color(0xff44911B)
            )
        }
    }
    LaunchedEffect(key1 = true){
        delay(2000)
        navController.navigate("Main")
    }

}