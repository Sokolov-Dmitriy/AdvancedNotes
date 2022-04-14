package com.sokolovds.myapplication.utils.viewModelUtils

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.sokolovds.myapplication.App
import com.sokolovds.myapplication.screens.MainActivityViewModel
import com.sokolovds.myapplication.screens.editNoteFragment.EditFragmentViewModel
import com.sokolovds.myapplication.screens.mainScreen.MainFragmentViewModel
import com.sokolovds.myapplication.screens.noteFragment.NoteFragmentViewModel

//class ViewModelFactory(private val app:App): ViewModelProvider.Factory{
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        val viewModel = when(modelClass){
//            MainFragmentViewModel::class.java -> {
//                MainFragmentViewModel(app.noteRepository)
//            }
//            else -> {
//                throw IllegalArgumentException("Unknown viewmodel class!")
//            }
//        }
//        return viewModel as T
//    }
//}
//
//fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

inline fun <reified VM : ViewModel> Fragment.viewModelsFactory() = viewModels<VM> {
    val application = requireActivity().application as App

    //получаем экземпляр MainActivityViewModel

    val provider =
        ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory(application))
    val viewModel = provider[MainActivityViewModel::class.java]

    ViewModelFactory(application,viewModel,this)
}

class ViewModelFactory(val app:App, val viewModel: MainActivityViewModel, owner: SavedStateRegistryOwner) : AbstractSavedStateViewModelFactory(owner,null) {
    override fun <T : ViewModel?> create(
        key: String,
        //modelClass - наша вью модель
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        val vm = when(modelClass){
            MainFragmentViewModel::class.java->{
                MainFragmentViewModel(app.noteRepository,viewModel)
            }
            NoteFragmentViewModel::class.java->{
                NoteFragmentViewModel(app.noteRepository,viewModel)
            }
            EditFragmentViewModel::class.java->{
                EditFragmentViewModel(app.noteRepository,viewModel)
            }
            else -> {
                throw throw IllegalArgumentException("Unknown viewmodel class!")
            }
        }
        return vm as T
    }

}