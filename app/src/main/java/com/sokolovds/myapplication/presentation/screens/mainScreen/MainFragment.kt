package com.sokolovds.myapplication.presentation.screens.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.presentation.base.BaseFragment
import com.sokolovds.myapplication.databinding.MainFragmentBinding
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.notes.NoteItemDecoration
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.notes.NotesAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainFragmentBinding, MainFragmentViewModel>() {

    override val viewModel by viewModel<MainFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiInit()
        buttonsInit()
    }

    private fun uiInit() {
        recyclerViewInit()
    }

    private fun buttonsInit() {
        binding.addNoteBtn.setOnClickListener {
            viewModel.onAddNotePressed()
        }
        binding.retryBtn.setOnClickListener {
            viewModel.onRetryPressed()
        }
    }

    private fun recyclerViewInit() {
        val notesAdapter = NotesAdapter(viewModel)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                notesAdapter.notes = it.notes
                binding.progressBar.root.isVisible = it.isLoading
                binding.retryBtn.isVisible = it.loadingIsFail
            }
        }
        with(binding.notesRecyclerView) {
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter = notesAdapter
            addItemDecoration(
                NoteItemDecoration(
                    resources.getDimensionPixelOffset(R.dimen.grid_layout_small_spacing),
                    resources.getDimensionPixelOffset(R.dimen.grid_layout_large_spacing)
                )
            )
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = MainFragmentBinding.inflate(inflater, container, false)

}