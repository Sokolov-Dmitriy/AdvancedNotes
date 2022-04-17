package com.sokolovds.myapplication.presentation.utils.viewModelUtils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.sokolovds.domain.useCases.*
import com.sokolovds.myapplication.App
import com.sokolovds.myapplication.presentation.screens.MainActivityViewModel
import com.sokolovds.myapplication.presentation.screens.editNoteFragment.EditFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.mainScreen.MainFragmentViewModel
import com.sokolovds.myapplication.presentation.screens.noteFragment.NoteFragmentViewModel


//inline fun <reified VM : ViewModel> Fragment.viewModelsFactory() = viewModels<VM> {
//    val application = requireActivity().application as App
//    val provider =
//        ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(application))
//    val viewModel = provider[MainActivityViewModel::class.java]
//
//    ViewModelFactory(application, viewModel, this)
//}
//
//class ViewModelFactory(
//    val app: App,
//    val viewModel: MainActivityViewModel,
//    owner: SavedStateRegistryOwner
//) : AbstractSavedStateViewModelFactory(owner, null) {
//
//    private val addNoteUseCase = AddNoteUseCase(app.noteRepository)
//    private val addListenerUseCase = AddListenerUseCase(app.noteRepository)
//    private val removeListenerUseCase = RemoveListenerUseCase(app.noteRepository)
//    private val deleteNoteByIdUseCase = DeleteNoteByIdUseCase(app.noteRepository)
//    private val editNoteUseCase = EditNoteUseCase(app.noteRepository)
//    private val getNoteByIdUseCase = GetNoteByIdUseCase(app.noteRepository)
//    private val getAllNotesUseCase = GetAllNotesUseCase(app.noteRepository)
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(
//        key: String,
//        modelClass: Class<T>,
//        handle: SavedStateHandle
//    ): T {
//        val vm = when (modelClass) {
//            MainFragmentViewModel::class.java -> {
//                MainFragmentViewModel(
//                    addListenerUseCase,
//                    removeListenerUseCase,
//                    getAllNotesUseCase,
//                    getNoteByIdUseCase,
//                    viewModel
//                )
//            }
//            NoteFragmentViewModel::class.java -> {
//                NoteFragmentViewModel(
//                    addNoteUseCase,
//                    viewModel
//                )
//            }
//            EditFragmentViewModel::class.java -> {
//                EditFragmentViewModel(
//                    editNoteUseCase,
//                    deleteNoteByIdUseCase,
//                    viewModel
//                )
//            }
//            else -> {
//                throw throw IllegalArgumentException("Unknown viewmodel class!")
//            }
//        }
//        return vm as T
//    }
//
//}