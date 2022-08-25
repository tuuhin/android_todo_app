package com.example.android_todo_app.presentation.screens

sealed class Routes(val route: String) {
    object ViewTodo : Routes("view")
    object CreateToDo : Routes("create")
}
