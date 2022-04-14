package com.sokolovds.myapplication.screens.noteFragment

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

class NoteFragment:Fragment() {

    @Parcelize
    class Data : FragmentData

    private lateinit var binding: NoteFragmentBinding
    private val viewModel by viewModelsFactory<NoteFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NoteFragmentBinding.inflate(inflater,container,false)
        uiInit()

        return binding.root
    }

    private fun uiInit(){
        buttonsInit()
    }

    private fun buttonsInit(){
        binding.saveBtn.setOnClickListener {
            viewModel.onSavePressed(Note(
                title = binding.title.text.toString(),
                description = binding.noteText.text.toString()
            ))
            viewModel.navigator.goBack()
        }

    }
}