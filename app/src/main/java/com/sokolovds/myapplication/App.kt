package com.sokolovds.myapplication

import android.app.Application
import com.sokolovds.myapplication.repository.NotesRepository
import com.sokolovds.myapplication.repository.NotesRepositoryImp

class App : Application() {

    val noteRepository: NotesRepository = NotesRepositoryImp()
}