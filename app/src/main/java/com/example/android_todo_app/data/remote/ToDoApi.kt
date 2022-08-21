package com.example.android_todo_app.data.remote


import com.example.android_todo_app.data.remote.dto.CreateToDoDto
import com.example.android_todo_app.data.remote.dto.DetailedInfoDto
import com.example.android_todo_app.data.remote.dto.ToDoDto
import com.example.android_todo_app.data.remote.dto.UpdateTodoDto
import retrofit2.http.*

interface ToDoApi {

    @GET("/items")
    suspend fun getToDos(@Query("skip") skip: Int, @Query("limit") limit: Int): List<ToDoDto>

    @GET("/items")
    suspend fun getCompletedTodo(@Query("checked") isCompleted: Boolean = true): List<ToDoDto>

    @POST("/item")
    suspend fun addToDo(@Body todo: CreateToDoDto): ToDoDto

    @PUT("/item")
    suspend fun updateTodo(@Body todo: UpdateTodoDto): ToDoDto

    @DELETE("/item/{id}")
    suspend fun deleteTodo(@Path("id") id: Int): DetailedInfoDto

    companion object {
        var BASE_URL: String = "https://calm-earth-06277.herokuapp.com"
//        FOR API DOCS
//         val DOCS:String = "https://calm-earth-06277.herokuapp.com/docs"
    }
}