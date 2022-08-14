package com.example.android_todo_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_todo_app.presentation.screens.*
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
                    ){
                        composable(Routes.ViewTodo.route){
                            ViewTodos(navController)
                        }
                        composable(Routes.CreateToDo.route){
                            CreateToDo(navController)
                        }
                    }
                }

            }
        }
    }
}
