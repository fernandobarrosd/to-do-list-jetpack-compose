package com.fernando.todolistjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fernando.todolistjetpackcompose.ui.components.TopBar
import com.fernando.todolistjetpackcompose.ui.routes.TasksRoute
import com.fernando.todolistjetpackcompose.ui.screens.tasksScreen.TasksScreen
import com.fernando.todolistjetpackcompose.ui.theme.Purple600
import com.fernando.todolistjetpackcompose.ui.theme.Purple900
import com.fernando.todolistjetpackcompose.ui.theme.ToDoListJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                Color.White.toArgb()
            )
        )
        setContent {
            ToDoListJetpackComposeTheme {
                App()
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    val isVisibleNavigationIcon = navController.currentBackStackEntry != null

    Scaffold(
        containerColor = Purple900,
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopBar(
                title = "Tasks",
                onNavigateBack = {
                    navController.popBackStack()
                },
                isVisibleNavigationIcon  = isVisibleNavigationIcon
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = Purple600,
                shape = RoundedCornerShape(50),
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add icon",
                    tint = Color.White
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = TasksRoute
        ) {
            composable<TasksRoute> { TasksScreen(paddingValues) }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AppPreview() {
    App()
}