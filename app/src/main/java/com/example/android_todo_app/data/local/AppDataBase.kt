package com.example.android_todo_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android_todo_app.data.local.dao.ToDoDao
import com.example.android_todo_app.data.local.entity.ToDoEntity

@Database(entities = [ToDoEntity::class], version = 1, exportSchema = false)
@TypeConverters(Adapters::class)
abstract class AppDataBase : RoomDatabase(){
    abstract fun todoDao(): ToDoDao
}