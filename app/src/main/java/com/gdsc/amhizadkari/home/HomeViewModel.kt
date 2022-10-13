package com.gdsc.amhizadkari.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.Routes
import com.gdsc.amhizadkari.data.Event

class HomeViewModel :ViewModel(){
    val imageList: List<Int> = mutableStateListOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
    )

    val pastEventList: List<Event> = mutableStateListOf(
        Event(1,"Independence day","25/20/2022",LoremIpsum(70).values.joinToString()),
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
    )

    val upcomingEventList: List<Event> = mutableStateListOf(
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
        Event(1,"Independence day","25/20/2022",null),
    )


    fun eventCardClick(item: Event, navController: NavController?) {
        navController?.navigate(Routes.EventScreen.route + "/${item.eventName}/${item.eventContent}")
    }
}