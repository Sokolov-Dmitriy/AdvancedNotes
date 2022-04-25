package com.sokolovds.myapplication.di

import com.sokolovds.domain.useCases.*
import org.koin.dsl.module


val domainModule = module {
    factory<AddNoteUseCase> {
        AddNoteUseCase(
            repository = get()
        )
    }

    factory<DeleteNoteByIdUseCase> {
        DeleteNoteByIdUseCase(
            repository = get()
        )
    }

    factory<EditNoteUseCase> {
        EditNoteUseCase(
            repository = get()
        )
    }

    factory<GetAllNotesUseCase> {
        GetAllNotesUseCase(
            repository = get()
        )
    }


}