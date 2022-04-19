package com.sokolovds.myapplication.presentation.base

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.databinding.NoteFragmentBinding

typealias OnDeleteBtnAction = () -> Unit

open class BaseNoteFragment : BaseFragment<NoteFragmentBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.title.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            (activity as AppCompatActivity).supportActionBar?.title = p0
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = NoteFragmentBinding.inflate(inflater, container, false)

}

