package com.example.keep.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface EntryDao {
    @Insert
    fun insertEntries(vararg entries: EntryEntity)

    @Insert
    fun insertTimeStamps(vararg timeStamps: TimeStampEntity)

    @Insert
    fun insertImageLists(vararg imageLists: ImageListEntity)

    @Insert
    fun insertTexts(vararg texts: TextEntity)

    @Delete
    fun deleteTimeStamp(timeStamp: TimeStampEntity)

    @Delete
    fun deleteImageLists(vararg imageLists: ImageListEntity)

    @Delete
    fun deleteTexts(vararg texts: TextEntity)

    @Query("SELECT * FROM EntryEntity WHERE date = :date LIMIT 1")
    fun getTodaysEntry(date: String): EntryEntity

    @Query("SELECT * FROM TimeStampEntity WHERE entryId = :entryId")
    fun getTimeStampsForEntry(entryId: String): List<TimeStampEntity>

    @Query("SELECT * FROM ImageListEntity, TextEntity WHERE timeStampId = :timeStampId")
    fun getTimeStampElements(timeStampId: Int): List<TimeStampElementEntity>
}