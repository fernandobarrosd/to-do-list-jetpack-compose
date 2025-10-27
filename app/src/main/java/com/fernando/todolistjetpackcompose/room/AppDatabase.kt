package com.fernando.todolistjetpackcompose.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fernando.todolistjetpackcompose.room.daos.TaskDAO
import com.fernando.todolistjetpackcompose.room.entities.Task

@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDAO() : TaskDAO
}