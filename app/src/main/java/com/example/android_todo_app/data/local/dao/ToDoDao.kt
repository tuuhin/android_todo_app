package com.example.android_todo_app.data.local.dao

import androidx.room.*
import com.example.android_todo_app.data.local.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity")
    suspend fun getToDos() : List<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE isCompleted=1")
    suspend fun getCompletedToDos():List<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE isCompleted=0")
    suspend fun getInCompletedToDos():List<ToDoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(todo: ToDoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodos(todos:List<ToDoEntity>)

    @Delete
    suspend fun removeToDo(todo: ToDoEntity)
}