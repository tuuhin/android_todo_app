package com.example.android_todo_app.data.repository

import com.example.android_todo_app.data.local.dao.ToDoDao
import com.example.android_todo_app.data.remote.ToDoApi
import com.example.android_todo_app.domain.Resource
import com.example.android_todo_app.domain.models.CreateTodoModel
import com.example.android_todo_app.domain.models.ToDoModel
import com.example.android_todo_app.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TodoRepositoryImplementation(
    private val api: ToDoApi,
    private val db: ToDoDao
) : ToDoRepository {
    override suspend fun getAllTodos(): Flow<Resource<List<ToDoModel>>> {
        return flow {
            emit(Resource.Loading())
            val todosInDb = db.getAllToDos().map { it.toModel() }
            try {
                val getTodos: List<ToDoModel> = api.getToDos(0, 100)
                    .map { it.toModel() }
                db.deleteTodos(getTodos.map { it.id })
                db.addTodos(getTodos.map { it.toEntity() })
                emit(Resource.Success(data = db.getAllToDos().map { it.toModel() }))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Can't get data from server.Loading data from cache",
                        data = todosInDb
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "IO exception occur somewhere",
                        data = todosInDb
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Unknown Exception Occurred "))
            }

        }
    }

    override suspend fun getAllInCompletedTodos(): Flow<Resource<List<ToDoModel>>> {
        return flow {
            emit(Resource.Loading())
            val getInCompletedTodosInDb: List<ToDoModel> =
                db.getInCompletedToDos().map { it.toModel() }
            try {
                val getCompletedTodos: List<ToDoModel> =
                    api.getCompletedTodo(isCompleted = false).map { it.toModel() }
                emit(Resource.Success(data = getCompletedTodos))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Can't connect to the server",
                        data = getInCompletedTodosInDb
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Io exception occurred",
                        data = getInCompletedTodosInDb
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Unknown Exception Occurred "))
            }
        }
    }

    override suspend fun getAllCompletedTodos(): Flow<Resource<List<ToDoModel>>> {
        return flow {
            emit(Resource.Loading())
            val getCompletedTodosInDb: List<ToDoModel> =
                db.getAllCompletedToDos().map { it.toModel() }
            try {
                val getCompletedTodos: List<ToDoModel> = api.getCompletedTodo().map { it.toModel() }
                emit(Resource.Success(data = getCompletedTodos))
            } catch (e: HttpException) {
                emit(
                    Resource.Error(
                        message = "Can't connect to the server",
                        data = getCompletedTodosInDb
                    )
                )
            } catch (e: IOException) {
                emit(
                    Resource.Error(
                        message = "Io exception occurred",
                        data = getCompletedTodosInDb
                    )
                )
            } catch (e: Exception) {
                e.printStackTrace()
                emit(Resource.Error(message = "Unknown Exception Occurred "))
            }
        }
    }

    override suspend fun createTodo(todo: CreateTodoModel): Resource<ToDoModel> {
        return try {
            val addTodo: ToDoModel = api.addToDo(todo.toDto()).toModel()
            db.addToDo(addTodo.toEntity())
            Resource.Success(data = addTodo)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "Http exception occurred ")
        } catch (e: IOException) {
            Resource.Error(message = "IO Exception occurred")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = "Unknown exception occurred")
        }
    }

    override suspend fun deleteTodo(todo: ToDoModel): Resource<Int> {
        return try {
            db.deleteTodoById(todo.toEntity())
            api.deleteTodo(todo.id)
            Resource.Success(data = 1)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "")
        } catch (e: IOException) {
            Resource.Error(message = "IO Exception Occurred")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = "Unknown Error occurred")
        }
    }

    override suspend fun updateTodo(todo: ToDoModel): Resource<ToDoModel> {
        return try {
            api.updateTodo(todo.toUpdateDto())
            db.updateTodo(todo.toEntity())
            val localUpdatedTodo = db.getTodoById(todo.id).toModel()
            Resource.Success(data = localUpdatedTodo)
        } catch (e: HttpException) {
            Resource.Error(message = e.message ?: "Http exception occurred ")
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "IO exception")
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(message = "Unknown Error ${e.message} Occurred  ${e.localizedMessage}")
        }
    }

}