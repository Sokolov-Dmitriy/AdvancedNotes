package com.sokolovds.myapplication.presentation.screens.noteFragment

import androidx.lifecycle.viewModelScope
import com.sokolovds.domain.exceptions.*
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.models.onError
import com.sokolovds.domain.models.onSuccess
import com.sokolovds.domain.useCases.AddNoteUseCase
import com.sokolovds.myapplication.presentation.base.BaseViewModel
import com.sokolovds.myapplication.presentation.utils.MessageProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AddNoteFragmentViewModel(
    private val addNoteUseCase: AddNoteUseCase,
    private val messageProvider: MessageProvider
) : BaseViewModel() {

    data class UiState(
        val isLoading: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun onSavePressed(note: Note) = viewModelScope.launch {
        _uiState.emit(UiState(true))
        with(addNoteUseCase(note)) {
            onSuccess {
                navigateBack()
            }
            onError {
                _uiState.emit(UiState(false))
                showSnackBar(messageProvider.getMessageByException(it))
            }
        }
    }


    fun onDeletePressed() {
        navigateBack()
    }


}