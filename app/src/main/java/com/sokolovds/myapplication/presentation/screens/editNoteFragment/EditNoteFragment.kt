package com.sokolovds.myapplication.presentation.screens.editNoteFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sokolovds.myapplication.presentation.base.BaseNoteFragment
import com.sokolovds.domain.models.Note
import com.sokolovds.myapplication.presentation.base.OnDeleteBtnAction
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteFragment() : BaseNoteFragment<EditFragmentViewModel>() {

    private val args by navArgs<EditNoteFragmentArgs>()
    override val viewModel by viewModel<EditFragmentViewModel>()
    override val onDeleteAction: OnDeleteBtnAction = { viewModel.onDeletePressed(args.note.id) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        buttonsInit()
        initNoteFields()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                binding.progressBar.root.isVisible = it.isLoading
            }
        }
    }

    private fun initNoteFields() {
        binding.title.setText(args.note.title)
        binding.noteText.setText(args.note.description)
    }

    private fun buttonsInit() {
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(
                Note(
                    args.note.id,
                    binding.title.text.toString(),
                    binding.noteText.text.toString()
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = args.note.title
    }


}