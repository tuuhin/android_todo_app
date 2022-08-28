package com.example.android_todo_app.domain.repository

import com.example.android_todo_app.domain.Resource
import com.example.android_todo_app.domain.models.CreateTodoModel
import com.example.android_todo_app.domain.models.ToDoModel
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun getAllTodos(): Flow<Resource<List<ToDoModel>>>

    suspend fun getAllInCompletedTodos(): Flow<Resource<List<ToDoModel>>>

    suspend fun getAllCompletedTodos(): Flow<Resource<List<ToDoModel>>>

    suspend fun createTodo(todo: CreateTodoModel): Resource<ToDoModel>

    suspend fun deleteTodo(todo: ToDoModel): Resource<Int>

    suspend fun updateTodo(todo: ToDoModel): Resource<ToDoModel>

}