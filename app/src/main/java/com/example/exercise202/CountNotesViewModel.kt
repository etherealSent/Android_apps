package com.example.exercise202

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class CountNotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = (application as NotesApplication).noteRepository

    fun getCountNotes(): LiveData<Int> = repository.countNotes()

    fun addNote(text: String) = repository.insertNote(Note(0, text))
}