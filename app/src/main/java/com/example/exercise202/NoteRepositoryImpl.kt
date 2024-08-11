package com.example.exercise202

import androidx.lifecycle.LiveData
import java.util.concurrent.Executor

class NoteRepositoryImpl(
    private val executor: Executor,
    private val noteDao: NoteDao
) : NoteRepository {

    override fun insertNote(note: Note) {
        executor.execute {
            noteDao.insertNote(note)
        }
    }

    override fun getNotes(): LiveData<List<Note>> {
        return noteDao.loadAllNotes()
    }

    override fun countNotes(): LiveData<Int> {
        return noteDao.getCountNote()
    }

}