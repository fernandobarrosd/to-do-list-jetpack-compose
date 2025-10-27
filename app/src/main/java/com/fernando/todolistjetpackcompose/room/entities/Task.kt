package com.fernando.todolistjetpackcompose.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "task_id")
    var id: String = UUID.randomUUID().toString(),

    @ColumnInfo
    val title: String = "",

    @ColumnInfo
    val description: String = ""
)