package com.gdsc.amhizadkari.terms

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.Poppins

@Composable
fun TandCScreen(navController:NavController) {

    BackHandler {
            navController.popBackStack()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 20.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier.padding(start = 5.dp,end = 10.dp),
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                    Text(
                        text = stringResource(R.string.terms_and_condn),
                        fontFamily = Poppins,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = if(isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = stringResource(id = R.string.full_text_tandc),
                        textAlign = TextAlign.Justify,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
        }
    }

@Preview
@Composable
fun Tprev() {
    TandCScreen(navController = rememberNavController())
}
