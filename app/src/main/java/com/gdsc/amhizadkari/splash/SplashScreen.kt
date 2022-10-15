package com.gdsc.amhizadkari.splash

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.Routes
import com.gdsc.amhizadkari.ui.theme.Poppins
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController) {

    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context).components {
        add(ImageDecoderDecoder.Factory())
    }.build()


    Box(modifier = Modifier.fillMaxSize()) {
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
        ) {
            Spacer(modifier = Modifier.padding(230.dp))

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

            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(context).data(data = R.drawable.tree_loading).apply(block = {
                        size(Size.ORIGINAL)
                    }).build(),
                    imageLoader = imageLoader
                ),
                contentDescription =  "Gif",
                modifier = Modifier.size(120.dp)
                    .padding(top = 20.dp)
            )
        }
    }
    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate(Routes.BottomNav.route)
    }
}