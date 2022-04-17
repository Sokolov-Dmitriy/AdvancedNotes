package com.sokolovds.myapplication.presentation.navigator

import com.sokolovds.myapplication.presentation.navigator.FragmentData

interface Navigator {
    fun launchFragment(data: FragmentData)

    fun goBack(result: Any? = null)
}