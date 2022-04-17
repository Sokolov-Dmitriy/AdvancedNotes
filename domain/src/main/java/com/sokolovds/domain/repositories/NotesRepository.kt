package com.sokolovds.domain.repositories

import com.sokolovds.domain.models.Note

interface NotesRepository : Repository {

    fun getNotes(): List<Note>

    fun addNote(note: Note)

    fun addListeners(listener: NotesListener)

    fun removeListeners(listener: NotesListener)

    fun notifyListeners()

    fun getNoteById(id: Int): Note

    fun editNote(note: Note)

    fun deleteNoteById(id: Int)


}
typealias NotesListener = (List<Note>) -> Unit