package com.sokolovds.myapplication.presentation.screens.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sokolovds.domain.useCases.AddListenerUseCase
import com.sokolovds.domain.useCases.GetAllNotesUseCase
import com.sokolovds.domain.useCases.GetNoteByIdUseCase
import com.sokolovds.domain.useCases.RemoveListenerUseCase
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesListener

class MainFragmentViewModel(
    private val addListenerUseCase: AddListenerUseCase,
    private val removeListenerUseCase: RemoveListenerUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val notesListener: NotesListener = {
        _notes.postValue(it)
    }

    init {
        addListenerUseCase.execute(notesListener)
        _notes.postValue(getAllNotesUseCase.execute())
    }

    override fun onCleared() {
        removeListenerUseCase.execute(notesListener)
        super.onCleared()
    }



}

