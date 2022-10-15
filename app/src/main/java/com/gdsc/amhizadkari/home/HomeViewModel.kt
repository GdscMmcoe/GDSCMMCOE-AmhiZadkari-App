package com.gdsc.amhizadkari.home

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.Routes
import com.gdsc.amhizadkari.data.EventDatabase
import com.gdsc.amhizadkari.data.EventItem
import com.gdsc.amhizadkari.data.EventRepository

class HomeViewModel :ViewModel(){


    val imageList: List<Int> = mutableStateListOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
    )

    fun eventCardClick(item: EventItem, navController: NavController?) {
        navController?.navigate(Routes.EventScreen.route + "/${item.eventId}")
    }

    fun getPastEvents(context: Context): LiveData<List<EventItem>> {
        val eventDao = EventDatabase.getInstance(context).eventDao()
        val repository = EventRepository(eventDao)
        return repository.getPastData
    }

    fun getFutureEvents(context: Context): LiveData<List<EventItem>> {
        val eventDao = EventDatabase.getInstance(context).eventDao()
        val repository = EventRepository(eventDao)
         return repository.getFutureData
    }

}