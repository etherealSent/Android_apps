package com.example.exercise202.storage.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DogDao {

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertDogs(dogs: List<DogEntity>)

    @Query("DELETE FROM dogs")
    fun deleteAll()

    @Query("SELECT * FROM dogs")
    fun loadAllDogs(): LiveData<List<DogEntity>>
}