package com.sokolovds.myapplication.screens

import android.app.Application
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.models.frafmentData.FragmentData
import com.sokolovds.myapplication.navigator.Navigator
import com.sokolovds.myapplication.utils.Keys
import com.sokolovds.myapplication.utils.activityUtils.ActivityActionsManager

class MainActivityViewModel(
    val app: Application
) : AndroidViewModel(app), Navigator {

    val whenActivityActive = ActivityActionsManager<MainActivity>()

    override fun launchFragment(data: FragmentData) = whenActivityActive {
        launchNewScreen(it, data)
    }

    override fun goBack(result: Any?) = whenActivityActive {
        it.onBackPressed()
    }

    private fun launchNewScreen(
        activity: MainActivity,
        data: FragmentData,
        addToBackStack: Boolean = true
    ) {
        val fragment = data.javaClass.enclosingClass.getDeclaredConstructor().newInstance() as Fragment
        fragment.arguments = bundleOf(Keys.KEY_DATA to data)
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(null)
        transaction
            .replace(R.id.fragmentContainer, fragment)
            .commit()

    }

    override fun onCleared() {
        whenActivityActive.clearActionsQueue()
    }

}