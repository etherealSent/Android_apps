package com.example.exercise202.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exercise202.model.TVShow

@Dao
interface TVShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTVSHows(tvShows: List<TVShow>)

    @Query("SELECT * FROM tvShows")
    fun getTVShows(): List<TVShow>

}