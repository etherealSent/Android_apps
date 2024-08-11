package com.example.exercise202

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)
    @Query("SELECT * FROM notes")
    fun loadAllNotes(): LiveData<List<Note>>
    @Query("SELECT COUNT(*) FROM notes")
    fun getCountNote(): LiveData<Int>
}