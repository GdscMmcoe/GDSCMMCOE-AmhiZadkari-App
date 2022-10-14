package com.gdsc.amhizadkari.util

import android.os.Bundle
import com.gdsc.amhizadkari.data.EventItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


fun readData(){

   val storage = FirebaseStorage.getInstance().getReference().child("ImageFolder")

    var database = FirebaseDatabase.getInstance().reference
    database.child("Omkar").setValue(EventItem(123,"Independence Day","01/12/1","Loremkamd askdna skadmkadms asmdk nasmnd aelkandasndmandwqkn dasn s sada da w df dsf e f ds f f"));


    var getData = object : ValueEventListener {
        override fun onCancelled(pO: DatabaseError) {
            TODO("Not yet implemented")
        }

        override fun onDataChange(pO: DataSnapshot) {
            var sb = StringBuilder()
            for(i in pO.children){
                var title = i.child("title").  getValue()
                var desc = i.child("desc").getValue()
                sb.append("${i.key} $title $desc")
                println(sb);
                println(123);
//                    val tv1: TextView = findViewById(R.id.textView)
//                    tv1.setText()
            }
        }
    }
    database.addValueEventListener(getData)
    database.addListenerForSingleValueEvent(getData)

}
