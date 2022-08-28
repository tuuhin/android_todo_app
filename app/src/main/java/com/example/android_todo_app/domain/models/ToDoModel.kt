package com.example.android_todo_app.domain.models

import com.example.android_todo_app.data.local.entity.ToDoEntity
import com.example.android_todo_app.data.remote.dto.UpdateTodoDto
import java.time.LocalDateTime

data class ToDoModel(
    val id: Int,
    val title: String,
    val desc: String?,
    val isCompleted: Boolean,
    val createdAt: LocalDateTime
) {

    fun toEntity(): ToDoEntity = ToDoEntity(
        id = id,
        title = title,
        desc = desc,
        isCompleted = isCompleted,
        createdAt = createdAt
    )

    fun toUpdateDto(): UpdateTodoDto = UpdateTodoDto(
        id = id,
        isCompleted = isCompleted
    )

}
