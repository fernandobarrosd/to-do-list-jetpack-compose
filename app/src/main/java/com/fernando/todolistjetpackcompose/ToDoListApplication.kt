package com.fernando.todolistjetpackcompose

import android.app.Application
import com.fernando.todolistjetpackcompose.repositories.repositoryModule
import com.fernando.todolistjetpackcompose.room.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ToDoListApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@ToDoListApplication)
            modules(
                roomModule,
                repositoryModule
            )
        }
    }
}