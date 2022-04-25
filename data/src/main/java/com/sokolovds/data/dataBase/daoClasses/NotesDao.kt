package com.sokolovds.data.dataBase.daoClasses;

import androidx.room.*
import com.sokolovds.data.dataBase.entities.NoteDBEntity
import com.sokolovds.data.dataBase.tuples.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Update(
        entity = NoteDBEntity::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun updateNote(note: NoteDBEntity)

    @Insert(
        entity = NoteDBEntity::class,
        onConflict = OnConflictStrategy.IGNORE
    )
    suspend fun createNote(note: NoteDBEntity)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): Flow<List<NoteDBEntity>>

    @Delete(entity = NoteDBEntity::class)
    suspend fun deleteNoteById(id: NoteId)

    @Query(
        "SELECT * FROM notes WHERE id = :id"
    )
    suspend fun getNoteById(id: Int): NoteDBEntity

}
