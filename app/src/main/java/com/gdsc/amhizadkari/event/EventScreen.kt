package com.gdsc.amhizadkari.event

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.ui.theme.Poppins

//private val repository: EventRepository
//
//init {
//    val EventDao = EventDatabase.getInstance(context).todoDao()
//    repository = EventRepository(EventDao)
//}


@Composable
fun EventScreen(
    title: String,
    content: String,
    navController: NavController?,
    viewModel: EventViewModel = viewModel()
) {
    val imageList = viewModel.imageList


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {


            LazyRow(
                modifier = Modifier.clip(RoundedCornerShape(25.dp))
            ) {
                items(imageList) { item ->
                    Image(
                        painter = painterResource(id = item),
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(height = 250.dp, width = 380.dp)
                            .padding(20.dp)
                            .clip(RoundedCornerShape(20.dp)),
                    )
                }
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, top = 15.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                Text(
                    text = content,
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
            }
        }
    }

    BackHandler {
        navController?.popBackStack()
    }
}


@Preview(showSystemUi = true)
@Composable
fun EventPrev() {
    EventScreen(title = "Independence Day", content = LoremIpsum(70).values.joinToString(),null)
}