package com.sokolovds.myapplication.di

import com.sokolovds.data.NotesRepositoryImp
import com.sokolovds.domain.repositories.NotesRepository
import org.koin.dsl.module

val dataModule = module {
    single<NotesRepository>{
        NotesRepositoryImp()
    }
}