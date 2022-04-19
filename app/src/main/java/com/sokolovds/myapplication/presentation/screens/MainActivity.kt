package com.sokolovds.myapplication.presentation.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStartDestination(
            getMainNavigationGraphId(),
            getNotesListDestination(),
            getFragmentContainer()
        )

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setStartDestination(
        mainNavigationGraphId: Int,
        destinationId: Int,
        fragmentContainer: Int
    ) {
        val navController = getRootNavController(fragmentContainer)
        val graph = navController.navInflater.inflate(mainNavigationGraphId)
        graph.setStartDestination(destinationId)
        navController.graph = graph
    }

    private fun getRootNavController(containerId: Int): NavController {
        val navHost =
            supportFragmentManager.findFragmentById(containerId) as NavHostFragment
        return navHost.navController
    }

    private fun getMainNavigationGraphId() = R.navigation.nav_graph

    private fun getNotesListDestination() = R.id.mainFragment

    private fun getFragmentContainer() = R.id.fragmentContainer

}