package com.sokolovds.domain.useCases

import com.sokolovds.domain.exceptions.AppException
import com.sokolovds.domain.exceptions.wrapRepositoryExceptionUnit
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesRepository

class AddNoteUseCase(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: Note) = wrapRepositoryExceptionUnit {
        repository.addNote(note)
    }
}