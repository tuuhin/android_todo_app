package com.example.android_todo_app.data.remote

import com.example.android_todo_app.data.remote.dto.ToDoDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ToDoApi {

    @GET("/items")
    suspend fun getToDos(
        @Query("skip") skip:Int = 0,
        @Query("limit") limit:Int = 0
    ):List<ToDoDto>

    @POST("/items")
    suspend fun addToDo(
        @Body todo:ToDoDto
    ):ToDoDto



    companion object {
        var BASE_URL = "https://calm-earth-06277.herokuapp.com"
    }
}