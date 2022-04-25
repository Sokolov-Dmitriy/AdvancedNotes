package com.sokolovds.myapplication.presentation.screens.noteFragment

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.sokolovds.myapplication.presentation.base.BaseNoteFragment
import com.sokolovds.domain.models.Note
import com.sokolovds.myapplication.presentation.base.OnDeleteBtnAction
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : BaseNoteFragment<AddNoteFragmentViewModel>() {

    override val viewModel by viewModel<AddNoteFragmentViewModel>()
    override val onDeleteAction: OnDeleteBtnAction = { viewModel.onDeletePressed() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiInit()
    }


    private fun uiInit() {
        buttonsInit()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                binding.progressBar.root.isVisible = it.isLoading
            }
        }
    }

    private fun buttonsInit() {
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(
                Note(
                    title = binding.title.text.toString(),
                    description = binding.noteText.text.toString()
                )
            )
        }
        binding.progressBar.root.setOnClickListener(null)

    }
}