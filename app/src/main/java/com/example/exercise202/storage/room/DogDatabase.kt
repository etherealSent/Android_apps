package com.example.exercise202.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DogEntity::class],
    version = 1
)
abstract class DogDatabase : RoomDatabase() {

    abstract fun dogDao(): DogDao
}