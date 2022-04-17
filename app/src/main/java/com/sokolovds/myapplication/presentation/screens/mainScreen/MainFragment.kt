package com.sokolovds.myapplication.presentation.screens.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.presentation.base.BaseFragment
import com.sokolovds.myapplication.databinding.MainFragmentBinding
import com.sokolovds.myapplication.presentation.navigator.FragmentData
import com.sokolovds.myapplication.presentation.screens.noteFragment.NoteFragment
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.NoteItemDecoration
import com.sokolovds.myapplication.presentation.utils.recyclerViewUtils.NotesAdapter
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment() {

    @Parcelize
    class Data : FragmentData

    private val viewModel by viewModel<MainFragmentViewModel>()
    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        uiInit()
        buttonsInit()
        return binding.root
    }

    private fun uiInit() {
        recyclerViewInit()
    }

    private fun buttonsInit() {
        binding.addNoteBtn.setOnClickListener {
            viewModel.onAddNotePressed()
        }
    }

    private fun recyclerViewInit() {
        val adapter = NotesAdapter(viewModel)
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
}