package com.example.keep.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [
            EntryEntity::class,
            ImageListEntity::class,
            TextEntity::class,
            TimeStampEntity::class
        ],
    version = 1
)
abstract class KeepDataBase: RoomDatabase() {
    abstract fun entryDao(): EntryDao
}