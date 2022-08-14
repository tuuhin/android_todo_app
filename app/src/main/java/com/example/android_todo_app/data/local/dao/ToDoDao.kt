package com.example.android_todo_app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.android_todo_app.data.local.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity")
    suspend fun getAllToDos() : List<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE isCompleted=1")
    suspend fun getAllCompletedToDos():List<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE isCompleted=0")
    suspend fun getInCompletedToDos():List<ToDoEntity>

    @Query("DELETE FROM ToDoEntity WHERE id IN (:todosIds) ")
    suspend fun deleteTodos(todosIds: List<Int>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(todo: ToDoEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodos(todos:List<ToDoEntity>)

}