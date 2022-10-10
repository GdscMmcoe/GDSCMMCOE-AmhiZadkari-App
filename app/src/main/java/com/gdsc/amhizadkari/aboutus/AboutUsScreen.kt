package com.gdsc.amhizadkari.aboutus

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PeopleOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.Poppins
import com.gdsc.amhizadkari.ui.theme.linkBlue
import com.gdsc.amhizadkari.ui.theme.treeGreen

@Composable
fun AboutUsScreen(navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .size(45.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colors.treeGreen)
            ) {
                Icon(
                    imageVector = Icons.Default.PeopleOutline,
                    contentDescription = "Tree",
                    modifier = Modifier.padding(10.dp)
                )
                Text(
                    text = "About Us",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = MaterialTheme.colors.onBackground
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(
                    text = LoremIpsum(30).values.joinToString(),
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            ) {
                Text(text = "Find us on")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 5.dp)
                    .clickable {
                        val intent = Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("https://www.instagram.com/amhi_zadkari/")
                        )
                        context.startActivity(intent)
                    }
            )
             {

                Icon(
                    painter = painterResource(id = R.drawable.instagram),
                    contentDescription = "Instagram",
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 5.dp)
                )

                Text(
                    text = "amhi_zadkari",
                    color = MaterialTheme.colors.linkBlue
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun abtprev() {
    AboutUsScreen(navController = NavController(LocalContext.current))
}