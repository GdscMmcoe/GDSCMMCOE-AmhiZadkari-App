package com.gdsc.amhizadkari.data

import androidx.lifecycle.LiveData

class EventRepository(private val eventDatabaseDao:EventDatabaseDao) {
    val getAllData: LiveData<List<EventItem>> = eventDatabaseDao.getAll()

    suspend fun addEvent(eventItem: EventItem){
        eventDatabaseDao.insert(eventItem)
    }

    suspend fun updateEvent(eventItem: EventItem){
        eventDatabaseDao.update(eventItem)
    }

    suspend fun deleteEvent(eventItem: EventItem){
        eventDatabaseDao.delete(eventItem)
    }
}