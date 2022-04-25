package com.sokolovds.data


import com.sokolovds.data.dataBase.daoClasses.NotesDao
import com.sokolovds.data.dataBase.entities.NoteDBEntity
import com.sokolovds.data.dataBase.tuples.NoteId
import com.sokolovds.domain.exceptions.EmptyTitleException
import com.sokolovds.domain.models.Note
import com.sokolovds.domain.repositories.NotesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class NotesRepositoryImp(
    private val notesDao: NotesDao,
    private val dispatcher: CoroutineContext
) : NotesRepository {


    override fun getNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map { it.map { it.toNote() } }
    }

    override suspend fun addNote(note: Note) = withContext(dispatcher) {
        if (note.title.isEmpty()) throw EmptyTitleException()
        notesDao.createNote(
            NoteDBEntity(
                id = 0,
                title = note.title,
                description = note.description
            )
        )
    }

    override suspend fun editNote(note: Note) = withContext(dispatcher) {
        if (note.title.isEmpty()) throw EmptyTitleException()
        notesDao.updateNote(
            NoteDBEntity(
                id = note.id,
                title = note.title,
                description = note.description
            )
        )
    }

    override suspend fun deleteNoteById(id: Int) = withContext(dispatcher) {
        notesDao.deleteNoteById(NoteId(id))
    }


}