package com.example.android_todo_app.data.local

import androidx.room.TypeConverter
import java.util.*

class Adapters{
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}