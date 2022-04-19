package com.sokolovds.myapplication.di

import com.sokolovds.myapplication.presentation.navigator.Navigator
import com.sokolovds.myapplication.presentation.navigator.NavigatorImp
import com.sokolovds.myapplication.presentation.screens.MainActivityViewModel
import com.sokolovds.myapplication.presentation.screens.editNoteFragment.EditFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.mainScreen.MainFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.noteFragment.NoteFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.settingsFragment.SettingsFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.splash.SplashFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module


val appModule = module {

    viewModel<SettingsFragmentViewModel> {
        SettingsFragmentViewModel(
        )
    }

    viewModel<SplashFragmentViewModel> {
        SplashFragmentViewModel(
        )
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(
        )
    }

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            addListenerUseCase = get(),
            removeListenerUseCase = get(),
            getAllNotesUseCase = get(),
            getNoteByIdUseCase = get()

        )
    }

    viewModel<EditFragmentViewModel> {
        EditFragmentViewModel(
            editNoteUseCase = get(),
            deleteNoteByIdUseCase = get()
        )
    }

    viewModel<NoteFragmentViewModel> {
        NoteFragmentViewModel(
            addNoteUseCase = get()
        )
    }


}