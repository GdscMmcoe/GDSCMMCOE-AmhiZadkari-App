package com.gdsc.amhizadkari.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EventDatabaseDao {
    @Query("SELECT * FROM EventsTable")
    fun getAll():LiveData<List<EventItem>>

    @Query("SELECT * FROM EventsTable where eventId = :id")
    fun getEventById(id:Int):EventItem?

    @Insert
    suspend fun insert(item: EventItem)

    @Update
    suspend fun update(item: EventItem)

    @Delete
    suspend fun delete(item: EventItem)
}