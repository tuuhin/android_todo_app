package com.example.android_todo_app.domain.models

import com.example.android_todo_app.data.remote.dto.CreateToDoDto

data class CreateTodoModel(
    val title: String,
    val desc: String?,
    val isCompleted: Boolean
) {
    fun toDto(): CreateToDoDto = CreateToDoDto(
        title = title,
        desc = desc,
        isCompleted = isCompleted
    )
}