package com.fernando.todolistjetpackcompose.ui.screens.tasksScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.todolistjetpackcompose.ui.components.DeleteTaskDialog
import com.fernando.todolistjetpackcompose.ui.components.TaskItem
import com.fernando.todolistjetpackcompose.ui.components.VisibilityComposable
import com.fernando.todolistjetpackcompose.ui.theme.Purple900
import org.koin.androidx.compose.koinViewModel

@Composable
fun TasksScreen(paddingValues: PaddingValues) {
    val tasksViewModel = koinViewModel<TasksViewModel>()

    val tasks by tasksViewModel.tasks
        .observeAsState(emptyList())
    val isLoading by tasksViewModel.isLoading
        .observeAsState(true)
    val isShowDeleteAlert by tasksViewModel.isShowDeleteAlert
        .observeAsState(false)

    val selectedTask by tasksViewModel.selectedTask
        .observeAsState(null)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Purple900)
            .padding(paddingValues)
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        VisibilityComposable(isShowDeleteAlert) {
            DeleteTaskDialog(
                task = selectedTask!!,
                onDeleteTask = {
                    tasksViewModel.onDeleteTask(selectedTask?.id!!)
                },
                onCancelDialog = {
                    tasksViewModel.onCancelDialog()
                }
            )
        }
        if (isLoading) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
            return@Column
        }
        if (tasks.isEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Nenhuma tarefa cadastrada",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
        else {
            LazyColumn {
                items(tasks) { task ->
                    TaskItem(
                        task = task,
                        onDeleteTask = { taskID ->
                            tasksViewModel.onShowDeleteAlert(taskID)
                        },
                        onSelectTask = { task ->
                            Log.i("SELECTED_TASK", "taskID: ${task.id}")
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun TasksScreenPreview() {
    TasksScreen(PaddingValues())
}