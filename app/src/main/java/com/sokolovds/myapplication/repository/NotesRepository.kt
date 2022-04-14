package com.sokolovds.myapplication.repository

import com.sokolovds.myapplication.models.Note

interface NotesRepository : Repository {

    fun getNotes(): List<Note>

    fun addNote(note: Note)

    fun addListeners(listener: NotesListener)

    fun removeListeners(listener: NotesListener)

    fun notifyListeners()

    fun getCurrentNoteById(id: Int): Note

    fun editNote(note: Note)

    fun deleteNoteById(id: Int)


}

typealias NotesListener = (List<Note>) -> Unit