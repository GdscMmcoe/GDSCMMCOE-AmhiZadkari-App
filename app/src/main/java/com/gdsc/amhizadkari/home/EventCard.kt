package com.gdsc.amhizadkari.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gdsc.amhizadkari.data.EventItem
import com.gdsc.amhizadkari.ui.theme.CardColor
import com.gdsc.amhizadkari.ui.theme.Poppins


@Composable
fun EventCard(e: EventItem,onClick: ()-> Unit) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp,
        modifier = Modifier.size(120.dp, 160.dp)
            .clickable { onClick() },
        color = MaterialTheme.colors.CardColor
    ) {
        Column(
            modifier = Modifier.padding(7.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = e.eventName,
                    modifier = Modifier.padding(all = 4.dp),
                    fontSize = 12.sp,
                    fontFamily = Poppins,
                    fontWeight = FontWeight.Bold,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Event,
                    contentDescription = "Event",
                    modifier = Modifier.size(25.dp),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(3.dp)
            ) {
                Text(
                    text = e.eventDate,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}