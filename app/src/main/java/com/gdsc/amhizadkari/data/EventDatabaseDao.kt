package com.gdsc.amhizadkari.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDatabaseDao {
    @Query("SELECT * FROM EventsTable where type = 'past'")
    fun getPastEvents():LiveData<List<EventItem>>

    @Query("SELECT * FROM EventsTable where type = 'future'")
    fun getFutureEvents():LiveData<List<EventItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: EventItem)

    @Query("DELETE FROM EventsTable where type = 'future'")
    suspend fun deleteFuture()

    @Query("SELECT * FROM EventsTable where eventId = :id")
    suspend fun getEventById(id:Int):EventItem?

}