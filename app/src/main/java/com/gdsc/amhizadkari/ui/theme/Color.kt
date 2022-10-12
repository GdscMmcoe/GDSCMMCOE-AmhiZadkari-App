package com.gdsc.amhizadkari.ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)


val Colors.linkBlue:Color
@Composable
get() = if(isLight) Color(0xff0000ee) else Color(0xff00FFFF)

val Colors.rowColor:Color
@Composable
get() = if(isLight) Color(0xffACCAA7) else Color(0xFF0869E0)

val Colors.CardColor:Color
@Composable
get() = if(isLight) Color(0xffD8F2D4) else MaterialTheme.colors.secondary //Color(0xFF047500)

