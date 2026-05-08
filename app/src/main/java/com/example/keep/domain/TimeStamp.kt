package com.example.keep.domain

import kotlin.time.ExperimentalTime
import java.time.Instant

data class TimeStamp @OptIn(ExperimentalTime::class) constructor(
    val timeStampId: Int,
    val time: Instant,
    val timeStampElements: List<TimeStampElement>
    ){
    var currentTimeStampId = 0

    fun increaseTimeStampId(){
        currentTimeStampId++
    }
}



