package com.gdsc.amhizadkari

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.gdsc.amhizadkari.data.EventDatabase
import com.gdsc.amhizadkari.data.EventDatabaseDao
import com.gdsc.amhizadkari.data.EventItem
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EventDatabaseTest{
    private lateinit var eventDatabaseDao: EventDatabaseDao
    private lateinit var db: EventDatabase

    @Before
    fun createDb(){
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context,EventDatabase::class.java).allowMainThreadQueries().build()
        eventDatabaseDao = db.eventDao()
    }

    @After
    @Throws(IOException::class)
    fun deleteDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetTest() = runBlocking {
        val eventItem = EventItem(1,"Independence Day Special","10/10/2022",LoremIpsum(20).values.joinToString(),type = "past",url = "")
        eventDatabaseDao.insert(eventItem)
        val item = eventDatabaseDao.getEventById(1)
        assertEquals(item?.eventId,1)
    }
}