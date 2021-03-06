package com.sokolovds.myapplication.presentation.screens.editNoteFragment

import androidx.lifecycle.viewModelScope
import com.sokolovds.domain.useCases.DeleteNoteByIdUseCase
import com.sokolovds.domain.useCases.EditNoteUseCase
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.models.onError
import com.sokolovds.domain.models.onSuccess
import com.sokolovds.myapplication.presentation.base.BaseViewModel
import com.sokolovds.myapplication.presentation.utils.MessageProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditFragmentViewModel(
    private val editNoteUseCase: EditNoteUseCase,
    private val deleteNoteByIdUseCase: DeleteNoteByIdUseCase,
    private val messageProvider: MessageProvider
) : BaseViewModel() {

    data class UiState(
        val isLoading: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    fun onSavePressed(note: Note) = viewModelScope.launch {
        _uiState.emit(UiState(true))
        with(editNoteUseCase(note)) {
            onSuccess {
                navigateBack()
            }
            onError {
                _uiState.emit(UiState(false))
                showSnackBar(messageProvider.getMessageByException(it))
            }
        }
    }

    fun onDeletePressed(id: Int) = viewModelScope.launch {
        _uiState.emit(UiState(true))
        with(deleteNoteByIdUseCase(id)) {
            onSuccess {
                navigateBack()
            }
            onError {
                _uiState.emit(UiState(false))
                showSnackBar(messageProvider.getMessageByException(it))
            }
        }
    }
}