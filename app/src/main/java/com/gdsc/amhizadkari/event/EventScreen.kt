package com.gdsc.amhizadkari.event

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
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
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.google.accompanist.pager.*
import kotlin.math.absoluteValue

//private val repository: EventRepository
//
//init {
//    val EventDao = EventDatabase.getInstance(context).todoDao()
//    repository = EventRepository(EventDao)
//}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun EventScreen(
    title: String,
    content: String,
    navController: NavController?,
    viewModel: EventViewModel = viewModel()
) {
    val imageList = viewModel.imageList
    val pagerState = rememberPagerState()

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
                    .padding(start = 30.dp, top = 10.dp)
            ) {
                Text(
                    text = title,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }

            HorizontalPager(
                count = imageList.size,
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 20.dp),

                ) { page ->
                Image(
                    painter = painterResource(id = imageList[page]),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(height = 250.dp, width = 420.dp)
                        .padding(top = 20.dp, start = 5.dp, end = 5.dp, bottom = 10.dp)
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

            Card(
                elevation = 10.dp,
                backgroundColor = MaterialTheme.colors.CardColor,
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier.fillMaxSize()
                    .padding(start = 10.dp,end = 10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Text(
                        text = content,
                        fontFamily = Poppins,
                        fontWeight = FontWeight(500),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 20.dp,start = 10.dp,end = 10.dp, bottom = 10.dp)
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