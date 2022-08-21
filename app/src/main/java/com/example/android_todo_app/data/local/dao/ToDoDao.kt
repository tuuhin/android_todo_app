package com.example.android_todo_app.data.local.dao

import androidx.room.*
import com.example.android_todo_app.data.local.entity.ToDoEntity

@Dao
interface ToDoDao {

    @Query("SELECT * FROM ToDoEntity ORDER BY id DESC")
    suspend fun getAllToDos(): List<ToDoEntity>

    @Query("SELECT * FROM ToDoEntity WHERE isCompleted=1 ORDER BY id DESC")
    suspend fun getAllCompletedToDos(): List<ToDoEntity>

    @Query("SELECT * from ToDoEntity WHERE id=:todoId")
    suspend fun getTodoById(todoId: Int): ToDoEntity

    @Query("SELECT * FROM ToDoEntity WHERE isCompleted=0 ORDER BY id DESC")
    suspend fun getInCompletedToDos(): List<ToDoEntity>

    @Query("DELETE FROM ToDoEntity WHERE id IN (:todosIds) ")
    suspend fun deleteTodos(todosIds: List<Int>)

    @Query("DELETE FROM ToDoEntity WHERE id=:todoId")
    suspend fun deleteTodoById(todoId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(todo: ToDoEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTodos(todos: List<ToDoEntity>)

    @Update
    suspend fun updateTodo(todo: ToDoEntity)

}