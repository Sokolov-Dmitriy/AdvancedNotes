package com.sokolovds.myapplication.presentation.navigator

import androidx.navigation.NavDirections

sealed class NavigationAction {
    data class ToDirection(val directions: NavDirections) : NavigationAction()
    object Back : NavigationAction()
    data class ShowToast(val message: String) : NavigationAction()
    data class ShowSnackBar(val message: String) : NavigationAction()
}


