package com.sokolovds.myapplication.screens.noteFragment

import androidx.lifecycle.ViewModel
import com.sokolovds.myapplication.models.Note
import com.sokolovds.myapplication.navigator.Navigator
import com.sokolovds.myapplication.repository.NotesRepository

class NoteFragmentViewModel(
    private val repository: NotesRepository,
    val navigator: Navigator
):ViewModel() {

    fun onSavePressed(note:Note){
        repository.addNote(note)
    }

}