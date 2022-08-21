package com.example.android_todo_app.domain.models

import com.example.android_todo_app.data.local.entity.ToDoEntity
import com.example.android_todo_app.data.remote.dto.CreateToDoDto
import com.example.android_todo_app.data.remote.dto.ToDoDto
import com.example.android_todo_app.data.remote.dto.UpdateTodoDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

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

    fun toDto(): ToDoDto = ToDoDto(
        id = id,
        title = title,
        desc = desc,
        createdAt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSZ").format(createdAt),
        isCompleted = isCompleted
    )

    fun toCreateDto(): CreateToDoDto = CreateToDoDto(
        title = title,
        desc = desc,
        isCompleted = isCompleted
    )

    fun toUpdateDto(): UpdateTodoDto = UpdateTodoDto(
        id = id,
        isCompleted = isCompleted
    )

}
