package com.gdsc.amhizadkari.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.data.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel :ViewModel(){
    init {
        viewModelScope.launch {
            while (true) {
                delay(6000)
                nextImage()
            }
        }
    }

    val imageList: List<Int> = mutableStateListOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4,
        R.drawable.image5,
        R.drawable.image6,
    )

    val pastEventList: List<Event> = mutableStateListOf(
        Event(1,"Independence day","25/20/2022",null),
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

    var index by mutableStateOf(0)

    private fun nextImage(){
        index++
        if(index >= imageList.size){
            index = 0
        }
    }
}