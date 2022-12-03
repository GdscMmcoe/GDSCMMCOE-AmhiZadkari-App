package com.gdsc.amhizadkari.event

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins


@Composable
fun EventScreen(
    id: Int,
    navController: NavController,
    viewModel: EventViewModel = viewModel()
) {
    viewModel.getDetails(LocalContext.current,id)

    val data = viewModel.details


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colors.onBackground,
                        )
                    }
                },
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        Text(
                            text = data?.eventName ?: "",
                            fontFamily = Poppins,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colors.onBackground
                        )
                        Text(
                            text = data?.eventDate ?: "",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Poppins,
                            color = MaterialTheme.colors.onBackground
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
                .verticalScroll(rememberScrollState())
                .padding(it)
        ) {
            AsyncImage(
                model = data?.url,
                placeholder = painterResource(id = R.drawable.placeholder),
                error = painterResource(id = R.drawable.placeholder),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(height = 250.dp, width = 420.dp)
                    .padding(top = 20.dp, start = 5.dp, end = 5.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Card(
                elevation = 10.dp,
                backgroundColor = MaterialTheme.colors.CardColor,
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        text = data?.eventContent?:"",
                        fontFamily = Poppins,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(
                            top = 20.dp,
                            start = 10.dp,
                            end = 10.dp,
                            bottom = 10.dp
                        )
                    )
                }
            }
        }
    }

    BackHandler {
        navController.popBackStack()
    }
}


@Preview
@Composable
fun EventPrev() {
    EventScreen(id = 1, navController = NavController(LocalContext.current))
}