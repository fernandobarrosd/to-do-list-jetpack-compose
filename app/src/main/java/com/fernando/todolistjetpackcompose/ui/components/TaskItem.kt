package com.fernando.todolistjetpackcompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fernando.todolistjetpackcompose.room.entities.Task
import com.fernando.todolistjetpackcompose.ui.theme.Green600
import com.fernando.todolistjetpackcompose.ui.theme.Red600
import com.fernando.todolistjetpackcompose.ui.theme.poppinsFontFamily

@Composable
fun TaskItem(task: Task, onDeleteTask: (String) -> Unit, onSelectTask: (task: Task) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = {
                    onSelectTask(task)
                },
                onClick = {}
            )
    ) {
        Box(
            modifier = Modifier.background(
                if (task.isFinish) Green600
                else Red600
            )
                .fillMaxWidth()
                .height(2.dp)
        )
        Column(
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = task.title,
                    fontSize = 14.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold
                )
                Row {
                    Button (
                        onClick = { onDeleteTask(task.id) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Red600
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .height(40.dp)
                    ) {
                        Text(
                            text = "Deletar",
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }
            Text(
                text = task.description,
                fontSize = 12.sp,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400
            )
        }
    }
    Spacer(Modifier.size(24.dp))
}

@Preview
@Composable
private fun TaskItemPreviewIsFinishTrue() {
    TaskItem(
        Task(
            title = "Task 1",
            description = "Task 1 description"
        ),
        onDeleteTask = { taskID -> },
        onSelectTask = { task -> }
    )
}

@Preview
@Composable
private fun TaskItemPreviewIsFinishFalse() {
    TaskItem(
        Task(
            title = "Task 1",
            description = "Task 1 description",
            isFinish = false
        ),
        onDeleteTask = { taskID -> },
        onSelectTask = { task -> }
    )
}