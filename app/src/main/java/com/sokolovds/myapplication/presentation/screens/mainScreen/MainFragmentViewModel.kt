package com.sokolovds.myapplication.presentation.screens.mainScreen

import androidx.lifecycle.viewModelScope
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.models.onError
import com.sokolovds.domain.models.onSuccess
import com.sokolovds.domain.useCases.GetAllNotesUseCase
import com.sokolovds.myapplication.presentation.base.BaseViewModel
import com.sokolovds.myapplication.presentation.models.toNoteParcelize
import com.sokolovds.myapplication.presentation.utils.MessageProvider
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.notes.NotesAdapter
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val messageProvider: MessageProvider
) : BaseViewModel(), NotesAdapter.AdapterListener {

    data class UiState(
        val notes: List<Note> = emptyList(),
        val isLoading: Boolean = false,
        val loadingIsFail: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState(isLoading = true))
    val uiState: StateFlow<UiState> = _uiState


    fun onAddNotePressed() {
        navigate(MainFragmentDirections.actionMainFragmentToNoteFragment())

    }

    fun onRetryPressed() {
        viewModelScope.launch {
            _uiState.emit(UiState(isLoading = true))
        }
        loadNotes()
    }


    init {
        loadNotes()
    }

    private fun loadNotes() {
        viewModelScope.launch {
            with(getAllNotesUseCase()) {
                onSuccess { listFlow ->
                    listFlow.collect {
                        _uiState.emit(UiState(notes = it))
                    }
                }
                onError {
                    _uiState.emit(UiState(loadingIsFail = true))
                    showSnackBar(messageProvider.getMessageByException(it, true))
                }
            }
        }
    }

    override fun onItemClick(note: Note) {
        navigate(MainFragmentDirections.actionMainFragmentToEditNoteFragment(note.toNoteParcelize()))
    }


}

