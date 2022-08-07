package com.example.android_todo_app.data.repository

import com.example.android_todo_app.data.local.dao.ToDoDao
import com.example.android_todo_app.data.remote.ToDoApi
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
            val todosInDb: List<ToDoModel> = db.getToDos().map { it.toModel() }
            emit(Resource.Success(data = todosInDb))
            try
            {
                val getAllTodos: List<ToDoModel> = api.getToDos().map { it.toModel() }

            }
            catch (e:HttpException){
                emit(Resource.Error(
                    data = todosInDb,
                    message = "Can't get data from server"
                ))
            }catch (e:IOException){
                emit(Resource.Error(
                    data = todosInDb,
                    message = "IO exception occur somewhere"
                ))
            }
        }
    }

    override suspend fun getAllIncompletedTodos(): Flow<Resource<List<ToDoModel>>> {
      TODO("Not yet Implemented")
    }

    override suspend fun getAllCompletedTodos(): Flow<Resource<List<ToDoModel>>> {
        TODO("Not yet implemented")
    }

    override suspend fun addTodo(todo: ToDoModel): Resource<ToDoModel> {

        TODO("Not yet implemented")
    }
}