package com.gdsc.amhizadkari.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EventItem::class], version = 1, exportSchema = false)
abstract class EventDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDatabaseDao

    companion object{
        private var INSTANCE: EventDatabase? = null

        fun getInstance(context: Context): EventDatabase{
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EventDatabase::class.java,
                        "todo_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}
