package com.example.keep.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keep.domain.Entry
import java.time.LocalDate

@Entity
data class EntryEntity (
    @PrimaryKey(autoGenerate = true) val date: String,
    val title: String,
    val imageOfTheDay: String,
    ){


    fun Entry.toData(): EntryEntity{
        return EntryEntity(
            date = date.toString(),
            title = title,
            imageOfTheDay = imageOfTheDay
        )
    }

    fun EntryEntity.toDomain(): Entry{
        return Entry(
            date = LocalDate.parse(date),
            title = title,
            imageOfTheDay = imageOfTheDay
        )
    }

}