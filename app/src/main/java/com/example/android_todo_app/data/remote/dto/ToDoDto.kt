package com.example.android_todo_app.data.remote.dto

import com.example.android_todo_app.domain.models.ToDoModel
import com.squareup.moshi.Json
import java.time.LocalDateTime

data class ToDoDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val desc: String?,
    @field:Json(name = "is_completed")
    val isCompleted: Boolean,
    @field:Json(name = "created_at")
    val createdAt: String
) {
    fun toModel(): ToDoModel {
        return ToDoModel(
            id = id,
            title = title,
            desc = desc,
            isCompleted = isCompleted,
            createdAt = LocalDateTime.parse(createdAt)
        )
    }

}
