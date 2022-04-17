package com.sokolovds.myapplication.presentation.utils.recyclerViewUtils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sokolovds.myapplication.databinding.NoteItemBinding
import com.sokolovds.domain.models.Note

class NotesAdapter(
    val listener: AdapterListener
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>(), View.OnClickListener {
    class NotesViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    var notes: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteItemBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        with(holder.binding) {
            root.tag = note
            noteTitle.text = note.title
            noteDescription.text = note.description
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onClick(p0: View) {
        val note = p0.tag as Note
        listener.onChangeId(note.id)

    }

    interface AdapterListener {
        fun onChangeId(id: Int)
    }
}