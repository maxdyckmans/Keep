package com.example.keep.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keep.domain.TimeStamp
import java.time.Instant

@Entity
data class TimeStampEntity(
    @PrimaryKey(autoGenerate = true) val timeStampId: Int,
    val time: String,
    val entryId: String
){
    fun TimeStamp.toData(entryId: String): TimeStampEntity{
        return TimeStampEntity(
            timeStampId = timeStampId,
            time = time.toString(),
            entryId = entryId
        )
    }
    fun TimeStampEntity.toDomain(): TimeStamp{
        return TimeStamp(
            timeStampId = timeStampId,
            time = Instant.parse(time),
        )
    }

}

