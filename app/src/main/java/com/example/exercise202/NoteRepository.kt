package com.example.exercise202

import androidx.lifecycle.LiveData

interface NoteRepository {

    fun insertNote(note: Note)

    fun getNotes(): LiveData<List<Note>>

    fun countNotes(): LiveData<Int>

}