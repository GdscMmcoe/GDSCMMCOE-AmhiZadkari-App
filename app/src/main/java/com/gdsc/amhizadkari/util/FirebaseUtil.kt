package com.gdsc.amhizadkari.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.gdsc.amhizadkari.data.EventDatabase
import com.gdsc.amhizadkari.data.EventItem
import com.gdsc.amhizadkari.data.EventRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

@Composable
fun ReadData(){
    val context = LocalContext.current
    val database = FirebaseDatabase.getInstance().reference


    val eventDao = EventDatabase.getInstance(context).eventDao()
    val repository = EventRepository(eventDao)

    val coroutineScope = rememberCoroutineScope()


    database.child("Events").get().addOnSuccessListener {
      for (i in it.children) {
          val e = mutableListOf<String>()
          for (j in i.children) {
              e.add(j.value.toString())
          }
          coroutineScope.launch {
              repository.deleteAllFutureEvents()
              repository.addEvent(EventItem(eventContent = e[0], eventDate = e[1], eventId = e[2].toInt(), url = e[3], eventName = e[4], type = e[5]))
          }
      }

    }

}
