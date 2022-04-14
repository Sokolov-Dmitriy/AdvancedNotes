package com.sokolovds.myapplication.screens.mainScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.base.BaseFragment
import com.sokolovds.myapplication.databinding.MainFragmentBinding
import com.sokolovds.myapplication.models.frafmentData.FragmentData
import com.sokolovds.myapplication.screens.noteFragment.NoteFragment
import com.sokolovds.myapplication.utils.recyclerViewUtils.NoteItemDecoration
import com.sokolovds.myapplication.utils.recyclerViewUtils.NotesAdapter
import com.sokolovds.myapplication.utils.viewModelUtils.viewModelsFactory
import kotlinx.parcelize.Parcelize

class MainFragment : BaseFragment() {

    @Parcelize
    class Data : FragmentData

    private val viewModel by viewModelsFactory<MainFragmentViewModel>()
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
            viewModel.navigator.launchFragment(NoteFragment.Data())
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