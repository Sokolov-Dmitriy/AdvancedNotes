package com.sokolovds.domain.useCases

import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesRepository

class GetNoteByIdUseCase(
    private val repository: NotesRepository
) {
    fun execute(noteId: Int): Note {
        return repository.getNoteById(noteId)
    }
}