package com.example.keep.domain

import java.time.LocalDate

//Class describing an entry in the Keep app
data class Entry (
    val date: LocalDate,
    val title: String,
    val imageOfTheDay: String = "",
    val timeStamps: List<TimeStamp> = emptyList(),

)