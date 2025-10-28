package com.fernando.todolistjetpackcompose.room.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fernando.todolistjetpackcompose.room.entities.Task

@Dao
interface TaskDAO {
    @Insert
    suspend fun saveTask(task: Task)

    @Query("SELECT * FROM tasks")
    suspend fun findAll() : List<Task>

    @Query("SELECT * FROM tasks WHERE task_id LIKE :taskID")
    fun findById(taskID: String) : Task?

    @Query("DELETE FROM tasks WHERE task_id LIKE :taskID")
    suspend fun deleteTaskByID(taskID: String)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()

    @Query("DELETE FROM tasks WHERE task_id IN (:ids)")
    suspend fun deleteByIds(ids: List<String>)

    @Query("UPDATE tasks SET is_finish = 1 WHERE task_id = :taskID")
    suspend fun updateTaskToFinish(taskID: String)
}