package com.sokolovds.myapplication.presentation.base

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import com.sokolovds.myapplication.R
import com.sokolovds.myapplication.databinding.NoteFragmentBinding

typealias OnDeleteBtnAction = ()->Unit

open class BaseNoteFragment : BaseFragment() {

    private var _binding: NoteFragmentBinding? = null
    protected val binding get() = _binding!!
    protected var onDeleteAction : OnDeleteBtnAction = {}

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NoteFragmentBinding.inflate(inflater, container, false)
        binding.title.addTextChangedListener(textWatcher)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.actionDelete ->{
                onDeleteAction()
                true
            }
            else ->{
                super.onOptionsItemSelected(item)
            }
        }

    }

    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            (activity as AppCompatActivity).supportActionBar?.title = p0
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }

}

