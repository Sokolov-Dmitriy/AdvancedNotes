package com.sokolovds.myapplication.presentation.screens.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragmentViewModel : ViewModel() {
    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState = _loadingState

    init {
        viewModelScope.launch {
            _loadingState.postValue(false)
            _loadingState.postValue(true)
        }
    }

}