package com.sokolovds.myapplication.screens.mainScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokolovds.myapplication.models.Note
import com.sokolovds.myapplication.navigator.Navigator
import com.sokolovds.myapplication.repository.NotesListener
import com.sokolovds.myapplication.repository.NotesRepository
import com.sokolovds.myapplication.repository.Repository
import com.sokolovds.myapplication.screens.editNoteFragment.EditNoteFragment
import com.sokolovds.myapplication.utils.recyclerViewUtils.NotesAdapter

class MainFragmentViewModel(
    val repository: NotesRepository,
    val navigator: Navigator
) : ViewModel(), NotesAdapter.AdapterListener {

    private val _notes: MutableLiveData<List<Note>> = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes

    private val _currentNoteId: MutableLiveData<Int> = MutableLiveData<Int>()
    val currentNoteId: LiveData<Int> = _currentNoteId

    private val notesListener: NotesListener = {
        _notes.postValue(it)
    }

    init {
        repository.addListeners(notesListener)
        _notes.postValue(repository.getNotes())
    }

    override fun onCleared() {
        repository.removeListeners(notesListener)
        super.onCleared()
    }

    override fun onChangeId(id: Int) {
        _currentNoteId.postValue(id)
        navigator.launchFragment(EditNoteFragment.Data(repository.getCurrentNoteById(id)))
    }


}