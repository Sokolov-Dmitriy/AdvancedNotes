package com.sokolovds.myapplication.di

import com.sokolovds.myapplication.presentation.screens.MainActivityViewModel
import com.sokolovds.myapplication.presentation.screens.editNoteFragment.EditFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.mainScreen.MainFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.noteFragment.AddNoteFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.settingsFragment.SettingsFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.splash.SplashFragmentViewModel
import com.sokolovds.myapplication.presentation.utils.MessageProvider
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {

    factory<MessageProvider> {
        MessageProvider(app=get())
    }


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
            getAllNotesUseCase = get(),
            messageProvider = get()
        )
    }

    viewModel<EditFragmentViewModel> {
        EditFragmentViewModel(
            editNoteUseCase = get(),
            deleteNoteByIdUseCase = get(),
            messageProvider = get()
        )
    }

    viewModel<AddNoteFragmentViewModel> {
        AddNoteFragmentViewModel(
            addNoteUseCase = get(),
            messageProvider = get()
        )
    }


}