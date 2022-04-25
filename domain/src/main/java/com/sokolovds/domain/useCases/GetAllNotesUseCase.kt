package com.sokolovds.domain.useCases

import com.sokolovds.domain.exceptions.wrapRepositoryExceptionFlow
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.models.Result
import com.sokolovds.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(
    private val repository: NotesRepository
) {
    operator fun invoke(): Result<Flow<List<Note>>> = wrapRepositoryExceptionFlow(){
        repository.getNotes()
    }
}