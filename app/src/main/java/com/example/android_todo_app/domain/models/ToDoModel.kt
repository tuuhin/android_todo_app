package com.example.android_todo_app.domain.models

import java.util.*

data class ToDoModel(
    val title:String,
    val desc:String?,
    val isCompleted:Boolean,
    val createdAt : Date
    )
