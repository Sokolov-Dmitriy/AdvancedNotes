package com.sokolovds.myapplication.presentation.screens.editNoteFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sokolovds.myapplication.presentation.base.BaseNoteFragment
import com.sokolovds.domain.models.Note
import com.sokolovds.myapplication.presentation.models.NoteParcelize
import com.sokolovds.myapplication.presentation.navigator.FragmentData
import com.sokolovds.myapplication.presentation.utils.Keys
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteFragment : BaseNoteFragment() {

    private val args by navArgs<EditNoteFragmentArgs>()
    private val viewModel by viewModel<EditFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        buttonsInit()
        initNoteFields()
    }

    private fun initNoteFields() {
        binding.title.setText(args.note.title)
        binding.noteText.setText(args.note.title)
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
            findNavController().popBackStack()
        }
        onDeleteAction = {
            viewModel.onDeletePressed(args.note.id)
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = args.note.title
    }


}