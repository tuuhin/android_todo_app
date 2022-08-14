package com.example.android_todo_app.data.remote


import com.example.android_todo_app.data.remote.dto.ToDoDto
import okhttp3.Call
import retrofit2.http.*


interface ToDoApi {

    @GET("/items")
    suspend fun getToDos(
        @Query("skip") skip:Int,
        @Query("limit") limit:Int
    ):List<ToDoDto>

    @GET("/items")
    suspend fun getCompletedTodo(
        @Query("checked") isCompleted:Boolean = true
    ):List<ToDoDto>

    @POST("/item")
    suspend fun addToDo(@Body todo:ToDoDto):ToDoDto

    @PUT("/item")
    suspend fun updateTodo(@Body todo: ToDoDto):ToDoDto

    @DELETE("/item/{id}")
    suspend fun deleteTodo(@Path("id") id:Int) : Call

    companion object {
        var BASE_URL:String = "https://calm-earth-06277.herokuapp.com"
    }
}