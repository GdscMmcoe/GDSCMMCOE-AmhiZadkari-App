package com.gdsc.amhizadkari.donate

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.ui.theme.buttonColor

@Composable
fun DonateUsScreen() {

    val context = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image6),
                    contentDescription = "Image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(height = 250.dp, width = 420.dp)
                        .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(20.dp))
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 45.dp)
                ) {
                    Text(
                        text = stringResource(R.string.save_future_text),
                        fontFamily = Poppins,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Card(
                    elevation = 5.dp,
                    backgroundColor = MaterialTheme.colors.CardColor,
                    shape = RoundedCornerShape(40.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 10.dp, end = 10.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, start = 35.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.donate_text),
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
                                .padding(horizontal = 20.dp)
                        ) {
                            Text(
                                text = LoremIpsum(70).values.joinToString(),
                                fontFamily = Poppins,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Button(
                        onClick = {
                            val url =
                                "upi://pay?pa=khushalsinhag@oksbi&pn=khushalsinha%20garud&aid=uGICAgIC1yv7NYw"
                            val intent = Intent("android.intent.action.VIEW", Uri.parse(url))
                            context.startActivity(intent)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(60.dp)
                            .padding(start = 55.dp, end = 55.dp)
                            .clip(RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.buttonColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = stringResource(R.string.donate_with_upi),
                            fontSize = 20.sp,
                            fontFamily = Poppins
                        )
                    }
                }
            }
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = 10.dp, top = 260.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tree),
                    contentDescription = "Tree",
                    modifier = Modifier.size(100.dp)
                )
            }
        }
    }
}