package com.sokolovds.myapplication.screens.editNoteFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sokolovds.myapplication.databinding.NoteFragmentBinding
import com.sokolovds.myapplication.models.Note
import com.sokolovds.myapplication.models.frafmentData.FragmentData
import com.sokolovds.myapplication.utils.viewModelUtils.viewModelsFactory
import kotlinx.parcelize.Parcelize

class EditNoteFragment:Fragment() {

    @Parcelize
    class Data(
        val note: Note
    ):FragmentData

    private lateinit var binding: NoteFragmentBinding
    private lateinit var data: Data
    private val viewModel by viewModelsFactory<EditFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteFragmentBinding.inflate(inflater,container,false)
        initUi()

        return binding.root
    }

    private fun initUi(){
        buttonsInit()
        initNoteFields()
    }

    private fun initNoteFields(){
        data = requireArguments().getParcelable<EditNoteFragment.Data>("key") as EditNoteFragment.Data
        binding.title.setText(data.note.title)
        binding.noteText.setText(data.note.description)
    }

    private fun buttonsInit(){
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(Note(data.note.id,binding.title.text.toString(),binding.noteText.text.toString()))
        }
    }
}