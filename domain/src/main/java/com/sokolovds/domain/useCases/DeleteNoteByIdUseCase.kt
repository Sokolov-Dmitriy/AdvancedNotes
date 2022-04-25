package com.sokolovds.domain.useCases

import com.sokolovds.domain.exceptions.AppException
import com.sokolovds.domain.exceptions.wrapRepositoryExceptionUnit
import com.sokolovds.domain.repositories.NotesRepository


class DeleteNoteByIdUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(noteId: Int) = wrapRepositoryExceptionUnit() {
        repository.deleteNoteById(noteId)
    }
}