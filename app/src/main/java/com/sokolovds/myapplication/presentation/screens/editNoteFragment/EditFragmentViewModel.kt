package com.sokolovds.myapplication.presentation.screens.editNoteFragment

import androidx.lifecycle.ViewModel
import com.sokolovds.domain.useCases.DeleteNoteByIdUseCase
import com.sokolovds.domain.useCases.EditNoteUseCase
import com.sokolovds.domain.models.Note

class EditFragmentViewModel(
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase
) : ViewModel() {

    fun onSavePressed(note: Note) {
        editNoteUseCase.execute(note)
    }

    fun onDeletePressed(noteId: Int) {
        deleteNoteByIdUseCase.execute(noteId)
    }
}