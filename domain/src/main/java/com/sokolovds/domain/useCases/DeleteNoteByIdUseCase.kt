package com.sokolovds.domain.useCases

import com.sokolovds.domain.repositories.NotesRepository


class DeleteNoteByIdUseCase(
    private val repository: NotesRepository
) {
    fun execute(noteId: Int) {
        repository.deleteNoteById(noteId)
    }
}