package com.sokolovds.myapplication.presentation.base

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.presentation.navigator.NavigationAction
import com.sokolovds.myapplication.presentation.navigator.observeNonNull

typealias OnDeleteBtnAction = () -> Unit

abstract class BaseFragment<B : ViewBinding, VM : BaseViewModel> : Fragment() {

    private var _binding: B? = null
    protected val binding get() = _binding!!

    protected open val onDeleteAction: OnDeleteBtnAction = {}

    protected abstract val viewModel: VM

    protected abstract fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation()
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(
            findNavController().backQueue.size > 2
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.app_name)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
        if (this !is BaseNoteFragment) menu.removeItem(R.id.actionDelete)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionDelete -> {
                onDeleteAction()
                true
            }
            R.id.actionSettings -> {
                findNavController().navigate(R.id.show_settings)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }

    private fun observeNavigation() {
        viewModel.navigation.observeNonNull(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { navigationCommand ->
                handleNavigation(navigationCommand)
            }
        }
    }

    private fun handleNavigation(navAction: NavigationAction) {
        when (navAction) {
            is NavigationAction.ToDirection -> findNavController().navigate(navAction.directions)
            is NavigationAction.Back -> findNavController().navigateUp()
            is NavigationAction.ShowToast -> Toast.makeText(
                requireContext(),
                navAction.message,
                Toast.LENGTH_SHORT
            ).show()
            is NavigationAction.ShowSnackBar -> Snackbar.make(
                requireView(),
                navAction.message,
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }


}