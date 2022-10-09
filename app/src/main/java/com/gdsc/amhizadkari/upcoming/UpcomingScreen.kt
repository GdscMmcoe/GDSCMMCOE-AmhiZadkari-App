package com.gdsc.amhizadkari.upcoming

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc.amhizadkari.ui.theme.Poppins

@Composable
fun UpcomingScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Our Upcoming Events",
                fontFamily = Poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }

    }
}