package com.sokolovds.myapplication.presentation.screens

import androidx.lifecycle.ViewModel
import com.sokolovds.myapplication.presentation.navigator.Navigator
import com.sokolovds.myapplication.presentation.navigator.NavigatorImp


class MainActivityViewModel(
    val navigator: NavigatorImp
) : ViewModel(), Navigator by navigator {

    override fun onCleared() {
        navigator.clear()
    }


}