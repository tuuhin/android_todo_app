package com.example.android_todo_app.data.remote.dto

import com.example.android_todo_app.domain.models.CreateTodoModel
import com.squareup.moshi.Json

data class CreateToDoDto(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val desc: String?,
    @field:Json(name = "is_completed")
    val isCompleted: Boolean,
) {
    fun toModel(): CreateTodoModel = CreateTodoModel(
        title = title,
        desc = desc,
        isCompleted = isCompleted
    )
}