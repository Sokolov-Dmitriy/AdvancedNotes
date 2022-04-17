package com.sokolovds.myapplication.presentation.navigator

import android.os.Bundle
import android.view.View
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.presentation.base.BaseFragment
import com.sokolovds.myapplication.presentation.screens.mainScreen.MainFragment
import com.sokolovds.myapplication.presentation.utils.Keys

class StackFragmentNavigator(
    private val activity: AppCompatActivity,
    @IdRes private val containerId: Int,
    private val initialScreenCreator: () -> FragmentData
) : Navigator {

    override fun launchFragment(data: FragmentData) {
        launchNewScreen(data)
    }

    override fun goBack(result: Any?) {
        activity.onBackPressed()
    }

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            launchNewScreen(initialScreenCreator(), false)
        }
        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallBack, false)
    }

    fun onDestroy() {
        activity.supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallBack)
    }

    private fun launchNewScreen(
        data: FragmentData,
        addToBackStack: Boolean = true
    ) {
        val fragment =
            data.javaClass.enclosingClass.getDeclaredConstructor().newInstance() as Fragment
        fragment.arguments = bundleOf(Keys.KEY_DATA to data)
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(null)
        transaction
            .replace(containerId, fragment)
            .commit()

    }

    private val fragmentCallBack = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            setDisplayHomeAsUp()
        }
    }

    private fun notifyChanged() {
        setDisplayHomeAsUp()
    }

    private fun setDisplayHomeAsUp() {
        if (activity.supportFragmentManager.backStackEntryCount > 0) {
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        } else {
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }
}

