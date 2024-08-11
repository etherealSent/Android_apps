package com.example.exercise202

import androidx.lifecycle.LiveData
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class CountNotesViewModelTest {

    private lateinit var countNotesViewModel: CountNotesViewModel

    @Mock
    lateinit var application: NotesApplication

    @Mock
    lateinit var noteRepository: NoteRepository

    @Before
    fun setUp() {
        whenever(application.noteRepository).thenReturn(noteRepository)
        countNotesViewModel = CountNotesViewModel(application)
    }

    @Test
    fun insertNote() {
        val text = "text"
        countNotesViewModel.addNote(text)
        verify(noteRepository).insertNote(Note(0, text))
    }

    @Test
    fun getNoteCountLiveData() {
        val notes = mock<LiveData<Int>>()
        whenever(noteRepository.countNotes()).thenReturn(notes)

        val result = countNotesViewModel.getCountNotes()

        assertEquals(notes, result)
    }
}