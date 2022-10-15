package com.gdsc.amhizadkari.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EventsTable")
data class EventItem(
    @PrimaryKey()
    var eventId: Int = 0,

    @ColumnInfo(name = "event_name")
    val eventName: String,

    @ColumnInfo(name = "event_date")
    val eventDate: String,

    @ColumnInfo(name = "event_content")
    val eventContent: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "url")
    val url: String
)