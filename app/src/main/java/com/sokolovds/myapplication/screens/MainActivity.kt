package com.sokolovds.myapplication.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.databinding.ActivityMainBinding
import com.sokolovds.myapplication.screens.mainScreen.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    val viewModel by viewModels<MainActivityViewModel> { ViewModelProvider.AndroidViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, MainFragment())
//                .add(R.id.fragmentContainer, NoteFragment())
                .commit()
        }

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        viewModel.whenActivityActive.setActive(this)
    }

    override fun onPause() {
        super.onPause()
        viewModel.whenActivityActive.setUnActive()
    }

    private fun notifyChanged(){

    }

    private val fragmentCallBack = object : FragmentManager.FragmentLifecycleCallbacks(){
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            notifyChanged()
        }
    }
}