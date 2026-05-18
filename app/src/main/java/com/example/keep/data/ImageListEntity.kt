package com.example.keep.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.keep.domain.TimeStampElement

@Entity
data class ImageListEntity(
    @PrimaryKey(autoGenerate = true) val imageListId: Int,
    val images : List<String>,
    override val timeStampId: Int
    ): TimeStampElementEntity{
    fun ImageListEntity.toDomain(): TimeStampElement.ImageList{
        return TimeStampElement.ImageList(
            elementId = imageListId,
            images = images
        )
    }

    //timeStampId needs to be provided on conversion
    fun TimeStampElement.ImageList.toData(timeStampId: Int): ImageListEntity{
        return ImageListEntity(
            imageListId = elementId,
            images = images,
            timeStampId = timeStampId
        )
    }
    }

