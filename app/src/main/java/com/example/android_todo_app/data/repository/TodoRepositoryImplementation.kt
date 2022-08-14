package com.example.android_todo_app.data.repository

import com.example.android_todo_app.data.local.dao.ToDoDao
import com.example.android_todo_app.data.local.entity.ToDoEntity
import com.example.android_todo_app.data.remote.ToDoApi
import com.example.android_todo_app.data.remote.dto.ToDoDto
import com.example.android_todo_app.domain.Resource
import com.example.android_todo_app.domain.models.ToDoModel
import com.example.android_todo_app.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class TodoRepositoryImplementation(
    private  val api:ToDoApi,
    private  val db: ToDoDao
):ToDoRepository{
    override suspend fun getAllTodos(): Flow<Resource<List<ToDoModel>>> {
        return flow {
            emit(Resource.Loading())
            val todosInDb = db.getAllToDos().map { it.toModel() }
            try
            {
                val getTodos: List<ToDoModel> = api.getToDos(0,100)
                    .map { it.toModel() }
                db.deleteTodos(getTodos.map { it.id })
                db.addTodos(getTodos.map { it.toEntity() })
            }
            catch (e:HttpException){
                emit(Resource.Error(
                    message = "Can't get data from server",
                    data = todosInDb
                ))
            }catch (e:IOException){
                emit(Resource.Error(
                    message = "IO exception occur somewhere",
                    data = todosInDb
                ))
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            emit(Resource.Success(data = db.getAllToDos().map { it.toModel() }))

        }
    }

    override suspend fun getAllInCompletedTodos(): Flow<Resource<List<ToDoModel>>> {
       return flow {
           emit(Resource.Loading())
           val getCompletedTodosInDb:List<ToDoModel> = db.getInCompletedToDos().map { it.toModel() }
           try {
               val getCompletedTodos :List<ToDoModel> = api.getCompletedTodo().map { it.toModel() }
               emit(Resource.Success(data = getCompletedTodos))
           }catch (e:HttpException){ emit(Resource.Error(message = "Can't connect to the server", data = getCompletedTodosInDb))
           }catch (e:IOException){emit(Resource.Error(message = "Io exception occured", data = getCompletedTodosInDb))
           }catch (e:Exception){ e.printStackTrace() }

       }
    }

    override suspend fun getAllCompletedTodos(): Flow<Resource<List<ToDoModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addTodo(todo: ToDoModel): Resource<ToDoModel> {
       try {
           val addTodo:ToDoModel = api.addToDo(todo.toDto()).toModel()
           db.addToDo(addTodo.toEntity())
           return Resource.Success(data = addTodo)
       }catch (e:HttpException){
            return   Resource.Error(message = "Cant connect to the server")
       }catch (e:IOException){
            return  Resource.Error(message = "IO Exception occurred")

       }catch (e:Exception){
            e.printStackTrace()
       }
      return  Resource.Error(message = "Unknown exception occured")
    }

    override suspend fun deleteTodo(todo: ToDoModel): Resource<ToDoModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateTodo(todo: ToDoModel): Resource<ToDoModel> {
        TODO("Not yet implemented")
    }

}