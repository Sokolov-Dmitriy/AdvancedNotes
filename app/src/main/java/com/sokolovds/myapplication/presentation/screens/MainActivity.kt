package com.sokolovds.myapplication.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.databinding.ActivityMainBinding
import com.sokolovds.myapplication.presentation.navigator.StackFragmentNavigator
import com.sokolovds.myapplication.presentation.screens.mainScreen.MainFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navigator: StackFragmentNavigator

    private val viewModel by viewModel<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigator = StackFragmentNavigator(this, R.id.fragmentContainer) {
            MainFragment.Data()
        }
        navigator.onCreate(savedInstanceState)

    }

    override fun onDestroy() {
        navigator.onDestroy()
        super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.navigator.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        viewModel.navigator.setNavigator(null)
    }


}