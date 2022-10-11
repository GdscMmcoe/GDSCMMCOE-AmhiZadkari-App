package com.gdsc.amhizadkari.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.ui.theme.treeGreen

@Preview(showSystemUi = true)
@Composable
fun Prev() {
    HomeScreen(null)
}

@Composable
fun HomeScreen(
    navController: NavController?,
    viewModel: HomeViewModel = viewModel()

) {
    val count = viewModel.imageList.size
    val current = viewModel.index
    val imageUri = viewModel.imageList[current]
    val pastEventList = viewModel.pastEventList
    val upcomingEventList = viewModel.upcomingEventList

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = imageUri),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(height = 250.dp, width = 420.dp)
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            DotsIndicator(
                dots = count,
                selected = current,
                modifier = Modifier.padding(bottom = 20.dp)
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colors.treeGreen)
            ) {
                Text(
                    text = "Past Events",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }
            LazyRow(
                contentPadding = PaddingValues(bottom = 15.dp)
            ){
                items(pastEventList) {item ->
                    Spacer(modifier = Modifier.padding(10.dp))
                    EventCard(item)
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colors.treeGreen)
            ) {
                Text(
                    text = "Upcoming Events",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }

            if(upcomingEventList.isEmpty()){
                Text(
                    text = "No Events yet, stay tuned for more!",
                    fontSize = 20.sp,
                    fontFamily = Poppins,
                    modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 30.dp)
                )
            }
            else {
                LazyRow(
                    contentPadding = PaddingValues(bottom = 15.dp)
                ) {
                    items(upcomingEventList) { item ->
                        Spacer(modifier = Modifier.padding(10.dp))
                        EventCard(e = item)
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun DotsIndicator(
    dots: Int,
    selected: Int,
    modifier: Modifier = Modifier
) {

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {

        items(dots) { index ->
            if (index == selected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.Blue)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(color = Color.LightGray)
                )
            }

            if (index != dots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}