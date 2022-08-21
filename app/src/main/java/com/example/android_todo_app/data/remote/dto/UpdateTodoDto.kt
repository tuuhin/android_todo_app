package com.example.android_todo_app.data.remote.dto

import com.squareup.moshi.Json

data class UpdateTodoDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "is_completed")
    val isCompleted: Boolean,
)
