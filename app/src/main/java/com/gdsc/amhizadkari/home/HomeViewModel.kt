package com.gdsc.amhizadkari.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.amhizadkari.R
import com.gdsc.amhizadkari.data.Events
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
        R.drawable.zadkari_logo,
        R.drawable.start_page
    )

    val eventList: List<Events> = mutableStateListOf(
        Events("Independence day","25/20/2022"),
        Events("Independence day","25/20/2022"),
        Events("Independence day","25/20/2022"),
        Events("Independence day","25/20/2022"),
        Events("Independence day","25/20/2022")
    )

    var index by mutableStateOf(0)

    private fun nextImage(){
        index++
        if(index >= imageList.size){
            index = 0
        }
    }
}