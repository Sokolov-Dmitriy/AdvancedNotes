package com.sokolovds.myapplication.presentation.screens.editNoteFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.sokolovds.myapplication.presentation.base.BaseNoteFragment
import com.sokolovds.domain.models.Note
import com.sokolovds.myapplication.presentation.models.NoteParcelize
import com.sokolovds.myapplication.presentation.navigator.FragmentData
import com.sokolovds.myapplication.presentation.utils.Keys
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNoteFragment : BaseNoteFragment() {

    @Parcelize
    class Data(
        val note: NoteParcelize
    ) : FragmentData


    private lateinit var data: Data
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
        data = requireArguments().getParcelable<Data>(Keys.KEY_DATA) as Data
        binding.title.setText(data.note.title)
        binding.noteText.setText(data.note.description)
    }

    private fun buttonsInit() {
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(
                Note(
                    data.note.id,
                    binding.title.text.toString(),
                    binding.noteText.text.toString()
                )
            )
        }
        onDeleteAction = {
            viewModel.onDeletePressed(data.note.id)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = data.note.title
    }


}