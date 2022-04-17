package com.sokolovds.myapplication.presentation.screens.noteFragment

import androidx.lifecycle.ViewModel
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.useCases.AddNoteUseCase
import com.sokolovds.myapplication.presentation.navigator.Navigator
import com.sokolovds.myapplication.presentation.navigator.NavigatorImp

class NoteFragmentViewModel(
    private val addNoteUseCase: AddNoteUseCase,
    private val navigator: Navigator
) : ViewModel() {

    fun onSavePressed(note: Note) {
        addNoteUseCase.execute(note)
        navigator.goBack()
    }

    fun onDeletePressed() {
        navigator.goBack()
    }

}