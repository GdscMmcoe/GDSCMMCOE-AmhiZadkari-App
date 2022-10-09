package com.gdsc.amhizadkari.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    Box(modifier = Modifier.fillMaxSize()) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.zadkari_logo),
            contentDescription = "Main Logo",
            modifier = Modifier
                .size(90.dp)
                .padding(10.dp)
        )
    }


    }
}