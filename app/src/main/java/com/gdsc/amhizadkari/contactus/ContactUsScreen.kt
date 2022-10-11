package com.gdsc.amhizadkari.contactus

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ContactUsScreen(navController: NavController) {
    BackHandler {
        navController.popBackStack()
    }
}