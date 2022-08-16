package com.example.android_todo_app.presentation.navigation

import com.example.android_todo_app.domain.models.ToDoModel

data class TodoState(
    val todos: List<ToDoModel> = emptyList(),
    val isLoading: Boolean = true
)
