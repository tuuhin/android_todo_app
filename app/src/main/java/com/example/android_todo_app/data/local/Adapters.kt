package com.example.android_todo_app.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import java.util.*

@ProvidedTypeConverter
class Adapters{

    @TypeConverter
    fun fromDate(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun toDate(date: Date): Long {
        return date.time
    }
}