package com.sokolovds.myapplication.presentation.navigator

import com.sokolovds.myapplication.presentation.utils.activityUtils.ResourceActionsManager

class NavigatorImp : Navigator {

    private val targetNavigator = ResourceActionsManager<Navigator>()

    override fun launchFragment(data: FragmentData) = targetNavigator {
        it.launchFragment(data)
    }

    override fun goBack(result: Any?) = targetNavigator {
        it.goBack()
    }

    fun setNavigator(navigator: Navigator?) {
        targetNavigator.resource = navigator
    }

    fun clear() {
        targetNavigator.clearActionsQueue()
    }
}