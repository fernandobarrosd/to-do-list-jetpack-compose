package com.fernando.todolistjetpackcompose.repositories

import com.fernando.todolistjetpackcompose.room.daos.TaskDAO
import com.fernando.todolistjetpackcompose.room.entities.Task

class TaskRepository(private val taskDAO: TaskDAO) {
    suspend fun saveTask(task: Task) {
        taskDAO.saveTask(task)
    }

    suspend fun findAllTasks() : List<Task> {
        return taskDAO.findAll()
    }

    suspend fun findTaskById(taskID: String) : Task? {
        return taskDAO.findById(taskID)
    }

    suspend fun deleteAllTasks() {
        taskDAO.deleteAll()
    }

    suspend fun deleteTaskById(taskID: String) {
        taskDAO.deleteTaskByID(taskID)
    }

    suspend fun deleteTasksByIds(tasksIds: List<String>) {
        taskDAO.deleteByIds(tasksIds)
    }

    suspend fun updateTaskToFinish(taskID: String) {
        taskDAO.updateTaskToFinish(taskID)
    }
}