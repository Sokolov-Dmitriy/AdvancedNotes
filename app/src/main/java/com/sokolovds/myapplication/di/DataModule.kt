package com.sokolovds.myapplication.di

import androidx.room.Room
import com.sokolovds.data.NotesRepositoryImp
import com.sokolovds.data.dataBase.DataBase
import com.sokolovds.data.dataBase.daoClasses.NotesDao
import com.sokolovds.domain.repositories.NotesRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val dataModule = module {


    single<NotesDao> {
        Room.databaseBuilder(get(), DataBase::class.java, "db_notes").build().getNotesDao()
    }

    factory<CoroutineContext> { Dispatchers.IO }

    single<NotesRepository> {
        NotesRepositoryImp(
            notesDao = get(),
            dispatcher = get()
        )
    }
}