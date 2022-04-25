package com.sokolovds.myapplication.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.sokolovds.myapplication.presentation.navigator.Event
import com.sokolovds.myapplication.presentation.navigator.NavigationAction

abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableLiveData<Event<NavigationAction>>()
    val navigation: LiveData<Event<NavigationAction>> get() = _navigation

    fun navigate(navDirections: NavDirections) {
        _navigation.value = Event(NavigationAction.ToDirection(navDirections))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationAction.Back)
    }

    fun showToast(message: String) {
        _navigation.value = Event(NavigationAction.ShowToast(message))
    }

    fun showSnackBar(message: String) {
        _navigation.value = Event(NavigationAction.ShowSnackBar(message))
    }


}