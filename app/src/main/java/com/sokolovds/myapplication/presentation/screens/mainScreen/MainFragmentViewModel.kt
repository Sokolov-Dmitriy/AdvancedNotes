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
import com.sokolovds.myapplication.presentation.models.toNoteParcelize
import com.sokolovds.myapplication.presentation.navigator.Navigator
import com.sokolovds.myapplication.presentation.navigator.NavigatorImp
import com.sokolovds.myapplication.presentation.screens.MainActivityViewModel
import com.sokolovds.myapplication.presentation.screens.editNoteFragment.EditNoteFragment
import com.sokolovds.myapplication.presentation.screens.noteFragment.NoteFragment
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.NotesAdapter
import org.koin.core.instance.InstanceFactory
import org.koin.core.module.Module

class MainFragmentViewModel(
    private val addListenerUseCase: AddListenerUseCase,
    private val removeListenerUseCase: RemoveListenerUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
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
        addListenerUseCase.execute(notesListener)
        _notes.postValue(getAllNotesUseCase.execute())
    }

    override fun onCleared() {
        removeListenerUseCase.execute(notesListener)
        super.onCleared()
    }

    override fun onChangeId(id: Int) {
        _currentNoteId.postValue(id)
        navigator.launchFragment(
            EditNoteFragment.Data(
                getNoteByIdUseCase.execute(id).toNoteParcelize()
            )
        )
    }

    fun onAddNotePressed() {
        navigator.launchFragment(NoteFragment.Data())
    }


}

