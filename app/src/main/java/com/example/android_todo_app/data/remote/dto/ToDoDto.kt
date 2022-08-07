package com.example.android_todo_app.data.remote.dto

import com.example.android_todo_app.data.local.entity.ToDoEntity
import com.example.android_todo_app.domain.models.ToDoModel
import com.squareup.moshi.Json
import java.util.*

data class ToDoDto(
    @field:Json(name="title")
    val title:String,
    @field:Json(name="description")
    val desc:String?,
    @field:Json(name = "is_completed")
    val isCompleted:Boolean,
    @field:Json(name="created_at")
    val createdAt : Date
){
    fun toModel():ToDoModel{
        return ToDoModel(
            title=title,
            desc=desc,
            isCompleted=isCompleted,
            createdAt=createdAt
        )
    }

    fun toEntity():ToDoEntity{
        return  ToDoEntity(
            title = title,
            desc = desc,
            createdAt = createdAt,
            isCompleted = isCompleted
        )
    }

}
