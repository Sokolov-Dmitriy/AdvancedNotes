package com.sokolovds.myapplication

import android.app.Application
import com.sokolovds.myapplication.repository.NotesRepository
import com.sokolovds.myapplication.repository.NotesRepositoryImp
import com.sokolovds.myapplication.repository.Repository

class App:Application() {
//    val repositories:List<Repository> = listOf(
//        NotesRepositoryImp()
//    )

    val noteRepository: NotesRepository = NotesRepositoryImp()
}