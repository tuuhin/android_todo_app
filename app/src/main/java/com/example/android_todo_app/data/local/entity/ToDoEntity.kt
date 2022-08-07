package com.example.android_todo_app.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android_todo_app.domain.models.ToDoModel
import java.util.*

@Entity
data class ToDoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val desc: String?,
    val createdAt: Date,
    val isCompleted :Boolean
){
    fun toModel():ToDoModel{
        return ToDoModel(
            title = title,
            desc=desc,
            createdAt = createdAt,
            isCompleted = isCompleted
        )
    }
}