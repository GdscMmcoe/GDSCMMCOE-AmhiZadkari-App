package com.gdsc.amhizadkari.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.amhizadkari.ui.theme.Poppins
import androidx.lifecycle.viewmodel.compose.viewModel

//private val repository: EventRepository
//
//init {
//    val EventDao = EventDatabase.getInstance(context).todoDao()
//    repository = EventRepository(EventDao)
//}


@Composable
fun EventScreen(
    title: String,
    content1: String,
    content2: String,
    viewModel: EventViewModel = viewModel()
) {
    val imageList = viewModel.imageList

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, top = 15.dp)
            ){
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
                    .padding(start = 30.dp, bottom = 15.dp, end = 30.dp)
            ){
                Text(
                    text = content1,
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
            }
            LazyRow{
                items(imageList){item ->
                    Image(
                        painter = painterResource(id = item),
                        contentDescription = "Image",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .size(height = 250.dp, width = 380.dp)
                            .padding(25.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, bottom = 15.dp, end = 30.dp,top = 15.dp)
            ){
                Text(
                    text = content2,
                    fontFamily = Poppins,
                    fontWeight = FontWeight(500),
                    fontSize = 16.sp
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun EventPrev() {
    EventScreen(title = "Independence Day", content1 = LoremIpsum(40).values.joinToString(), content2 = LoremIpsum(100).values.joinToString())
}