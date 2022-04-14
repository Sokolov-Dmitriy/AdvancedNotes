package com.sokolovds.myapplication.navigator

import com.sokolovds.myapplication.models.frafmentData.FragmentData

interface Navigator {
    fun launchFragment(data: FragmentData)

    fun goBack(result: Any? = null)
}