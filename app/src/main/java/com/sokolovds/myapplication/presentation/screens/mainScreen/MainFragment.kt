package com.sokolovds.myapplication.presentation.screens.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.presentation.base.BaseFragment
import com.sokolovds.myapplication.databinding.MainFragmentBinding
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.notes.NoteItemDecoration
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.notes.NotesAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<MainFragmentBinding>() {

    private val viewModel by viewModel<MainFragmentViewModel>()

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
            findNavController().navigate(R.id.action_mainFragment_to_noteFragment)
        }

    }

    private fun recyclerViewInit() {
        val adapter = NotesAdapter(findNavController())
        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.notes = it
        }
        val layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
        with(binding) {
            notesRecyclerView.layoutManager = layoutManager
            notesRecyclerView.adapter = adapter
            notesRecyclerView.addItemDecoration(
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