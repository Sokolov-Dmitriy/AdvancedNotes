package com.sokolovds.myapplication.di

import com.sokolovds.myapplication.presentation.navigator.Navigator
import com.sokolovds.myapplication.presentation.navigator.NavigatorImp
import com.sokolovds.myapplication.presentation.screens.MainActivityViewModel
import com.sokolovds.myapplication.presentation.screens.editNoteFragment.EditFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.mainScreen.MainFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.noteFragment.NoteFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {
    val vm = single<MainActivityViewModel> {
        MainActivityViewModel(
            navigator = NavigatorImp()
        )
    } bind  Navigator::class



    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            addListenerUseCase = get(),
            removeListenerUseCase = get(),
            getAllNotesUseCase = get(),
            getNoteByIdUseCase = get(),
            navigator = get()

        )
    }

    viewModel<EditFragmentViewModel> {
        EditFragmentViewModel(
            editNoteUseCase = get(),
            deleteNoteByIdUseCase = get(),
            navigator = get()
        )
    }

    viewModel<NoteFragmentViewModel> {
        NoteFragmentViewModel(
            addNoteUseCase = get(),
            navigator = get()

        )
    }





}