package com.sokolovds.myapplication.presentation.screens.editNoteFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokolovds.domain.useCases.DeleteNoteByIdUseCase
import com.sokolovds.domain.useCases.EditNoteUseCase
import com.sokolovds.domain.models.Note
import com.sokolovds.myapplication.presentation.navigator.Navigator
import com.sokolovds.myapplication.presentation.navigator.NavigatorImp

class EditFragmentViewModel(
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase,
    val navigator: Navigator
) : ViewModel() {
    private val _currentNote: MutableLiveData<Note> = MutableLiveData<Note>()
    val currentNote: LiveData<Note> = _currentNote

    fun onSavePressed(note: Note) {
        editNoteUseCase.execute(note)
        navigator.goBack()
    }

    fun onDeletePressed(noteId: Int) {
        deleteNoteByIdUseCase.execute(noteId)
        navigator.goBack()
    }
}