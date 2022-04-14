package com.sokolovds.myapplication.screens.editNoteFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokolovds.myapplication.models.Note
import com.sokolovds.myapplication.navigator.Navigator
import com.sokolovds.myapplication.repository.NotesRepository

class EditFragmentViewModel(
    val repository: NotesRepository,
    val navigator: Navigator
) : ViewModel() {
    private val _currentNote:MutableLiveData<Note> = MutableLiveData<Note>()
    val currentNote: LiveData<Note> = _currentNote

    fun onSavePressed(note:Note){
        repository.editNote(note)
        navigator.goBack()
    }
}