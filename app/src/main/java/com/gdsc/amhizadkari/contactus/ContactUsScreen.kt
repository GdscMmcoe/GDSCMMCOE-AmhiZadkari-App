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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins


private val contactList =
    mutableListOf("Kaushal Singh Garud", "Pratima Garud", "Ketki", "Suraj Kakade", "Prateek")

@Composable
fun ContactUsScreen(navController: NavController) {
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
                text = stringResource(R.string.contact_us_text),
                fontFamily = Poppins,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = if(isSystemInDarkTheme()) Color.White else Color.Black
            )
        }
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
                var phoneNo = ""
                if (name == "Kaushal Singh Garud")
                    phoneNo = "9529968388"
                if (name == "Pratima Garud")
                    phoneNo = "9665859394"
                if (name == "Ketki")
                    phoneNo = "9112686229"
                if (name == "Suraj Kakade")
                    phoneNo = "9767257776"
                if (name == "Prateek")
                    phoneNo = "860013940"
                val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNo, null))
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