package com.gdsc.amhizadkari.contactus

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins


private val contactList =
    mutableListOf("Kaushal Singh Garud", "Pratima Garud", "Ketki", "Suraj Kakade", "Prateek")

@Composable
fun ContactUsScreen(navController: NavController?) {
    BackHandler {
        navController?.popBackStack()
    }
    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colors.background)
    ){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(
            text = "Contact Us",
            fontFamily = Poppins,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = if(isSystemInDarkTheme()) Color.White else Color.Black
        )
        LazyColumn(modifier = Modifier.padding(vertical = 10.dp)) {

            items(contactList) { name ->
                ListItem(name = name)
            }

        }
    }
    }
}

@Composable
fun ListItem(name: String) {
    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colors.CardColor,
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .padding(vertical = 5.dp, horizontal = 10.dp)
            .clickable {
                val intent = Intent(Intent.ACTION_DIAL)
                if (name == "Kushal Singh Garud")
                    intent.data = Uri.parse("9529968388")
                if (name == "Pratima Garud")
                    intent.data = Uri.parse("9665859394")
                if (name == "Ketki")
                    intent.data = Uri.parse("9112686229")
                if (name == "Suraj Kakade")
                    intent.data = Uri.parse("9767257776")
                if (name == "Prateek")
                    intent.data = Uri.parse("860013940")

                context.startActivity(intent)

            }
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = "contact",
                    modifier = Modifier.size(80.dp)
                )

            Column {
                Text(
                    text = name,
                    fontSize = 16.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Normal
                )
            }


        }
    }

}


@Preview(showBackground = true)
@Composable
fun ContactPrev() {
    ContactUsScreen(navController = null)
}