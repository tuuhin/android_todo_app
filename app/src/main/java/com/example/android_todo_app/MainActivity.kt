package com.example.android_todo_app

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.android_todo_app.presentation.screens.AllToDoRoute
import com.example.android_todo_app.presentation.screens.CreateToDoRoute
import com.example.android_todo_app.presentation.screens.Routes
import com.example.android_todo_app.ui.theme.Android_ToDo_AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.statusBarColor = ContextCompat.getColor(this, GreenAccent200.)
        setContent {
            val navController = rememberNavController()
            Android_ToDo_AppTheme {

                    NavHost(
                        navController = navController,
                        startDestination = Routes.CreateToDo.route
                    ){
                        composable(Routes.AllToDo.route){
                            AllToDoRoute(navController)
                        }
                        composable(Routes.CreateToDo.route){
                            CreateToDoRoute(navController)
                        }
                    }
                }
            }

    }
}
