package com.sokolovds.myapplication.screens.noteFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.base.BaseNoteFragment
import com.sokolovds.myapplication.databinding.NoteFragmentBinding
import com.sokolovds.myapplication.models.Note
import com.sokolovds.myapplication.models.frafmentData.FragmentData
import com.sokolovds.myapplication.utils.viewModelUtils.viewModelsFactory
import kotlinx.parcelize.Parcelize

class NoteFragment : BaseNoteFragment() {

    @Parcelize
    class Data : FragmentData

    private val viewModel by viewModelsFactory<NoteFragmentViewModel>()

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
        }
        onDeleteAction = {
            viewModel.onDeletePressed()
        }

    }
}