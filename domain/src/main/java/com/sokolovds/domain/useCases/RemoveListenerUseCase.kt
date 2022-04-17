package com.sokolovds.domain.useCases

import com.sokolovds.domain.repositories.NotesListener
import com.sokolovds.domain.repositories.NotesRepository


class RemoveListenerUseCase(
    private val repository: NotesRepository
) {
    fun execute(listener: NotesListener) {
        repository.removeListeners(listener)
    }
}