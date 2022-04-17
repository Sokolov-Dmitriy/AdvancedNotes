package com.sokolovds.domain.useCases

import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesRepository

class GetAllNotesUseCase(
    private val repository: NotesRepository
) {
    fun execute(): List<Note> {
        return repository.getNotes()
    }
}