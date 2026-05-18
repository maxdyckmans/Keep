package com.example.keep.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keep.domain.TimeStampElement

@Entity
data class TextEntity(
    @PrimaryKey(autoGenerate = true) val textId: Int,
    val text: String,
    override val timeStampId: Int


): TimeStampElementEntity{
    fun TextEntity.toDomain(): TimeStampElement.Text{
        return TimeStampElement.Text(
            elementId = textId,
            text = text
        )
    }

    //timeStampId needs to be provided on conversion
    fun TimeStampElement.Text.toData(timeStampId: Int): TextEntity{
        return TextEntity(
            textId = elementId,
            text = text,
            timeStampId = timeStampId
        )
    }
}

