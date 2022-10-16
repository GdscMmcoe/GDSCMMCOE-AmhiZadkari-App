package com.gdsc.amhizadkari.data

import androidx.lifecycle.LiveData

class EventRepository(private val eventDatabaseDao:EventDatabaseDao) {

    val getPastData: LiveData<List<EventItem>> = eventDatabaseDao.getPastEvents()

    val getFutureData: LiveData<List<EventItem>> = eventDatabaseDao.getFutureEvents()

    suspend fun addEvent(eventItem: EventItem){
        eventDatabaseDao.insert(eventItem)
    }
    suspend fun getEventById(id:Int) = eventDatabaseDao.getEventById(id)

    suspend fun deleteAll(){
        eventDatabaseDao.deleteAll()
    }
}