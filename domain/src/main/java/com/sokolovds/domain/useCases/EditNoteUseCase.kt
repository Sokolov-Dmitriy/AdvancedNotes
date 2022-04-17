package com.sokolovds.domain.useCases

import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesRepository

class EditNoteUseCase(
    private val repository: NotesRepository
) {
    fun execute(note: Note) {
        repository.editNote(note)
    }
}