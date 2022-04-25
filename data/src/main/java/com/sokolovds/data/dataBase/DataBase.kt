package com.sokolovds.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sokolovds.data.dataBase.daoClasses.NotesDao
import com.sokolovds.data.dataBase.entities.NoteDBEntity

@Database(
    version = 2,
    entities = [
        NoteDBEntity::class
    ]
)
abstract class DataBase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}