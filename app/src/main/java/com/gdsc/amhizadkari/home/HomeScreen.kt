package com.gdsc.amhizadkari.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ScaleFactor
import androidx.compose.ui.layout.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.ui.theme.rowColor
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue


@Preview(showSystemUi = true)
@Composable
fun Prev() {
    HomeScreen(null)
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController?,
    viewModel: HomeViewModel = viewModel()

) {
    val count = viewModel.imageList.size
    val imageList = viewModel.imageList
    val pastEventList = viewModel.pastEventList
    val upcomingEventList = viewModel.upcomingEventList
    val pagerState = rememberPagerState()

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            HorizontalPager(
                count = count,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp),

            ) { page ->
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(height = 250.dp, width = 420.dp)
                        .padding(top = 5.dp,start = 5.dp, end = 5.dp, bottom = 10.dp)
                        .graphicsLayer {
                            val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                            lerp(
                                start = ScaleFactor(0.85f, 0.85f),
                                stop = ScaleFactor(1f, 1f),
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).also { scale ->
                                scaleX = scale.scaleX
                                scaleY = scale.scaleY
                            }
                            alpha = lerp(
                                start = ScaleFactor(0.5f, 0.5f),
                                stop = ScaleFactor(1f, 1f),
                                fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            ).scaleY
                        }
                        .clip(RoundedCornerShape(20.dp))
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp),
            )

            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(50.dp))
                    .background(MaterialTheme.colors.rowColor)
            ) {
                Text(
                    text = "Past Events",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins,
                    //color = Color.Black,
                    modifier = Modifier.padding(start = 20.dp)
                )
            }


            LazyRow(
                contentPadding = PaddingValues(bottom = 15.dp)
            ){
                items(pastEventList) {item ->
                    Spacer(modifier = Modifier.padding(10.dp))
                    EventCard(item){
                        viewModel.eventCardClick(item, navController)
                    }
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
                    .background(MaterialTheme.colors.rowColor)
            ) {
                Text(
                    text = "Upcoming Events",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(500),
                    fontFamily = Poppins,
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
                        EventCard(e = item){
                            viewModel.eventCardClick(item,navController)
                        }
                        Spacer(modifier = Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
}