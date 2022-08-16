package com.example.android_todo_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_todo_app.presentation.screens.CreateToDo
import com.example.android_todo_app.presentation.screens.Routes
import com.example.android_todo_app.presentation.screens.ViewTodos
import com.example.android_todo_app.ui.theme.Android_ToDo_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Android_ToDo_AppTheme {
                Surface(color = MaterialTheme.colors.surface) {
                    NavHost(
                        navController = navController,
                        startDestination = Routes.ViewTodo.route
                    ) {
                        composable(Routes.ViewTodo.route) {
                            ViewTodos(navController)
                        }
                        composable(Routes.CreateToDo.route) {
                            CreateToDo(navController)
                        }
                    }
                }

            }
        }
    }
}
