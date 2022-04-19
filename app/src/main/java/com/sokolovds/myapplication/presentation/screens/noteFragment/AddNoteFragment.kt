package com.sokolovds.myapplication.presentation.screens.noteFragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.sokolovds.myapplication.presentation.base.BaseNoteFragment
import com.sokolovds.domain.models.Note
import com.sokolovds.myapplication.presentation.navigator.FragmentData
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddNoteFragment : BaseNoteFragment() {

    private val viewModel by viewModel<NoteFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        uiInit()
    }


    private fun uiInit() {
        buttonsInit()
    }

    private fun buttonsInit() {
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(
                Note(
                    title = binding.title.text.toString(),
                    description = binding.noteText.text.toString()
                )
            )
            findNavController().popBackStack()
        }
        onDeleteAction = {
            findNavController().popBackStack()
        }

    }
}