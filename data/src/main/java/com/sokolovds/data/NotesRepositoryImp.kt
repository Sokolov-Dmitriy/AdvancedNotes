package com.sokolovds.data


import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesListener
import com.sokolovds.domain.repositories.NotesRepository


class NotesRepositoryImp : NotesRepository {

    override fun getNotes(): List<Note> {
        return notes.toList().sortedByDescending { it.id }
    }

    override fun addNote(note: Note) {
        var maxId: Int = note.id - 1
        if (note.id == -1) {
            maxId = notes.maxByOrNull { it.id }?.id ?: -1
        }
        notes.add(Note(maxId + 1, note.title, note.description))
        notifyListeners()
    }

    override fun addListeners(listener: NotesListener) {
        listeners.add(listener)
    }

    override fun removeListeners(listener: NotesListener) {
        listeners.remove(listener)
    }

    override fun notifyListeners() {
        listeners.forEach { it.invoke(this.getNotes()) }
    }

    override fun getNoteById(id: Int): Note {
        return notes.firstOrNull { it.id == id } ?: Note(-1, "error", "error")
    }

    override fun editNote(note: Note) {
        if (notes.removeAll { it.id == note.id }) {
            notes.add(note)
            notifyListeners()
        }

    }

    override fun deleteNoteById(id: Int) {
        notes.removeAll { it.id == id }
        notifyListeners()
    }

    private val listeners: MutableList<NotesListener> = emptyList<NotesListener>().toMutableList()


    private val notes: MutableList<Note> = mutableListOf(
        Note(
            1,
            "Помыть посуду перед сном",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            2,
            "Список покупок в магазине одежды",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe weferfewdf efwefew wefewf wefwe wef wef wef ef efwefw"
        ),
        Note(
            3,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            4,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            5,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            6,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            7,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwedwefef wefwefwe ewfewfewf  ewfwefwe wefwefwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            8,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            9,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        ),
        Note(
            10,
            "fefewf efwfe",
            " fefefef ewfwefwef efwefwe wefwe fwefewfewfwefwe wef wef ef efwefw"
        )
    )
}