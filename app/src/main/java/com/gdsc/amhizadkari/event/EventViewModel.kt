package com.gdsc.amhizadkari.event
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gdsc.amhizadkari.data.EventDatabase
import com.gdsc.amhizadkari.data.EventItem
import com.gdsc.amhizadkari.data.EventRepository
import kotlinx.coroutines.launch

class EventViewModel: ViewModel() {
    var details: EventItem? = null

    fun getDetails(context: Context,id:Int) {
        val eventDao = EventDatabase.getInstance(context).eventDao()
        val repository = EventRepository(eventDao)
        viewModelScope.launch {
            details = repository.getEventById(id)
        }
    }

}