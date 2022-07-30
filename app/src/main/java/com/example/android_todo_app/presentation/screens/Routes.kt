package com.example.android_todo_app.presentation.screens

sealed class Routes(val route:String){
    object AllToDo : Routes("all")
    object CreateToDo :Routes("create")
}