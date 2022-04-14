package com.sokolovds.myapplication.screens

import android.app.Application
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.models.frafmentData.FragmentData
import com.sokolovds.myapplication.navigator.Navigator
import com.sokolovds.myapplication.utils.activityUtils.ActivityActionsManager

class MainActivityViewModel(
    val app: Application
) : AndroidViewModel(app), Navigator {

    val whenActivityActive = ActivityActionsManager<MainActivity>()

    private val _result:MutableLiveData<Any> = MutableLiveData<Any>()
    val result: LiveData<Any> = _result


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
        println("launchNew")
        val fragment =
            data.javaClass.enclosingClass.getDeclaredConstructor().newInstance() as Fragment

        fragment.arguments = bundleOf("key" to data)

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