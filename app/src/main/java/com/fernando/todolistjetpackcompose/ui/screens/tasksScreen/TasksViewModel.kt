package com.fernando.todolistjetpackcompose.ui.screens.tasksScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fernando.todolistjetpackcompose.repositories.TaskRepository
import com.fernando.todolistjetpackcompose.room.entities.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TasksViewModel(private val taskRepository: TaskRepository) : ViewModel() {
    private val _tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _isShowDeleteAlert : MutableLiveData<Boolean> = MutableLiveData()
    val isShowDeleteAlert : LiveData<Boolean>
        get() = _isShowDeleteAlert

    private val _selectedTask: MutableLiveData<Task> = MutableLiveData()
    val selectedTask: LiveData<Task>
        get() = _selectedTask

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val tasks = taskRepository.findAllTasks()
            /*_tasks.postValue(
                listOf(
                    Task(title = "test1", description = "test1"),
                    Task(title = "test2", description = "test2", isFinish = true),
                    Task(title = "test3", description = "test3", isFinish = false)
                )
            )*/
            _tasks.postValue(tasks)
            _isLoading.postValue(false)
        }
    }

    fun onShowDeleteAlert(taskID: String) {
        viewModelScope.launch {
            val task = _tasks.value?.filter { task -> task.id == taskID }!!.first()

            _selectedTask.postValue(task)
            _isShowDeleteAlert.postValue(true)
        }
    }

    fun onCancelDialog() {
        _isShowDeleteAlert.postValue(false)
    }

    fun onDeleteTask(taskID: String) {
        viewModelScope.launch {
            taskRepository.deleteTaskById(taskID)
            val filteredTasks = _tasks.value?.filter { task ->
                task.id != taskID
            }

            _tasks.postValue(filteredTasks!!)
            _isShowDeleteAlert.postValue(false)

        }
    }
}