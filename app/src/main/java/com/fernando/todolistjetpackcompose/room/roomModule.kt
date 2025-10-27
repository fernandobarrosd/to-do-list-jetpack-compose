package com.fernando.todolistjetpackcompose.room

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun provideTaskDao(appDatabase: AppDatabase) = appDatabase.taskDAO()

val roomModule = module {
   single {
       Room.databaseBuilder(
           androidContext(),
           AppDatabase::class.java,
           "database"
       ).build()
   }

    single {
        provideTaskDao(get())
    }
}