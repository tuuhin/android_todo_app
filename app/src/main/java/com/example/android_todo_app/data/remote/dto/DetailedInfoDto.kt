package com.example.android_todo_app.data.remote.dto

import com.squareup.moshi.Json

data class DetailedInfoDto(
    @field:Json(name = "detail")
    val details: String
)
