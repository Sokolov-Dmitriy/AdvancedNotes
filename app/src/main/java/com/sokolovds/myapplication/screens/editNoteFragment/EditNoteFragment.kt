package com.sokolovds.myapplication.screens.editNoteFragment

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.sokolovds.myapplication.base.BaseNoteFragment
import com.sokolovds.myapplication.models.Note
import com.sokolovds.myapplication.models.frafmentData.FragmentData
import com.sokolovds.myapplication.utils.Keys
import com.sokolovds.myapplication.utils.viewModelUtils.viewModelsFactory
import kotlinx.parcelize.Parcelize

class EditNoteFragment:BaseNoteFragment() {

    @Parcelize
    class Data(
        val note: Note
    ):FragmentData


    private lateinit var data: Data
    private val viewModel by viewModelsFactory<EditFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi(){
        buttonsInit()
        initNoteFields()
    }

    private fun initNoteFields(){
        data = requireArguments().getParcelable<Data>(Keys.KEY_DATA) as Data
        binding.title.setText(data.note.title)
        binding.noteText.setText(data.note.description)
    }

    private fun buttonsInit(){
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(Note(data.note.id,binding.title.text.toString(),binding.noteText.text.toString()))
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