package com.example.exercise202

import android.app.Application
import androidx.room.Room
import java.util.concurrent.Executors

class NotesApplication : Application() {

    private lateinit var notesDatabase: NotesDatabase
    lateinit var noteRepository: NoteRepository

    override fun onCreate() {
        super.onCreate()

        notesDatabase = Room.databaseBuilder(applicationContext, NotesDatabase::class.java, "notes-db")
            .build()

        noteRepository = NoteRepositoryImpl(
            Executors.newSingleThreadExecutor(),
            notesDatabase.noteDao()
        )

    }
}