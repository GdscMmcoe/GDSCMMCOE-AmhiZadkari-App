package com.gdsc.amhizadkari.donate

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.Poppins

@Composable
fun DonateUsScreen(
    navController: NavController?,
    viewModel: DonateViewModel = viewModel()
) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = R.drawable.image6),
                contentDescription = "Image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(height = 250.dp, width = 420.dp)
                    .padding(top = 30.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
                    .clip(RoundedCornerShape(20.dp))
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Donate for a cause!",
                    fontFamily = Poppins,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = LoremIpsum(50).values.joinToString(),
                    fontFamily = Poppins
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Button(
                    onClick = { viewModel.donate() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(start = 55.dp, end = 55.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xff44911B),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Donate",
                        fontSize = 20.sp,
                        fontFamily = Poppins
                    )
                }
            }

        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DonatePrev() {
    DonateUsScreen(navController = null)
}