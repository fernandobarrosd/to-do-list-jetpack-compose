package com.fernando.todolistjetpackcompose.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fernando.todolistjetpackcompose.room.entities.Task
import com.fernando.todolistjetpackcompose.ui.theme.Green600
import com.fernando.todolistjetpackcompose.ui.theme.Red600
import com.fernando.todolistjetpackcompose.ui.theme.poppinsFontFamily

@Composable
fun DeleteTaskDialog(task: Task, onDeleteTask: (String) -> Unit, onCancelDialog: () -> Unit) {
    Dialog(
        onDismissRequest = onCancelDialog,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    text = "Deletar a tarefa",
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.size(4.dp))

                Text(
                    text = "Deseja deletar a tarefa?",
                    fontSize = 16.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.W400
                )
                Spacer(Modifier.size(32.dp))
                Row {
                    DeleteTaskButton(
                        text = "Sim",
                        backgroundColor = Green600,
                        onClick = { onDeleteTask(task.id) }
                    )
                    Spacer(Modifier.size(16.dp))
                    DeleteTaskButton(
                        text = "Não",
                        backgroundColor = Red600,
                        onClick = { onCancelDialog() }
                    )
                }

            }
        }
    }
}

@Preview
@Composable
private fun DeleteTaskDialogPreview() {
    DeleteTaskDialog(
        task = Task(title = "Task title",
            description = "Task description"),
        onDeleteTask = { taskID -> },
        onCancelDialog = {}
    )
}