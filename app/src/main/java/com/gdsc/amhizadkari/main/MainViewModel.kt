package com.gdsc.amhizadkari.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var selected = mutableStateOf(false)
}