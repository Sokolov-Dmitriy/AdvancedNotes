package com.sokolovds.myapplication

import android.app.Application
import com.sokolovds.data.NotesRepositoryImp
import com.sokolovds.domain.repositories.NotesRepository
import com.sokolovds.myapplication.di.appModule
import com.sokolovds.myapplication.di.dataModule
import com.sokolovds.myapplication.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule))
        }
    }

}