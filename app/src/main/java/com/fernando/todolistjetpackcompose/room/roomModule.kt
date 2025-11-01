package com.fernando.todolistjetpackcompose.room

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteConnection
import com.fernando.todolistjetpackcompose.room.entities.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun provideTaskDao(appDatabase: AppDatabase) = appDatabase.taskDAO()

val roomModule = module {
   single {
       Room.databaseBuilder(
           androidContext(),
           AppDatabase::class.java,
           "database"
       )
           .addCallback(object: RoomDatabase.Callback() {
               override fun onCreate(connection: SQLiteConnection) {
                   super.onCreate(connection)
                   val appDatabase = get<AppDatabase>()

                   val tasks = List(15) {
                       Task(
                           title = "Task ${it + 1}",
                           description = "Task ${it + 1} description",
                           isFinish = false
                       )
                   }


                   CoroutineScope(Dispatchers.IO).launch {
                       tasks.forEach { task -> appDatabase.taskDAO().saveTask(task) }
                   }
               }
           })
           .build()
   }

    singleOf(::provideTaskDao)
}