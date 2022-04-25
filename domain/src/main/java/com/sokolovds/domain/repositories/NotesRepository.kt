package com.sokolovds.domain.repositories

import com.sokolovds.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository : Repository {

    fun getNotes(): Flow<List<Note>>

    suspend fun addNote(note: Note)

    suspend fun editNote(note: Note)

    suspend fun deleteNoteById(id: Int)


}
