package com.gdsc.amhizadkari.event

import androidx.lifecycle.ViewModel
import androidx.room.Room
import com.gdsc.amhizadkari.R

class EventViewModel:ViewModel(){
    val imageList: List<Int> = listOf(
        R.drawable.start_page,
        R.drawable.zadkari_logo
    )
}