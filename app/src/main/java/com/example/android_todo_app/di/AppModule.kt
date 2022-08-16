package com.example.android_todo_app.di

import android.app.Application
import androidx.room.Room
import com.example.android_todo_app.data.local.Adapters
import com.example.android_todo_app.data.local.AppDataBase
import com.example.android_todo_app.data.remote.ToDoApi
import com.example.android_todo_app.data.repository.TodoRepositoryImplementation
import com.example.android_todo_app.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDataBase {
        return Room.databaseBuilder(app, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .addTypeConverter(Adapters())
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(): ToDoApi {
        return Retrofit
            .Builder()
            .baseUrl(ToDoApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ToDoApi::class.java)
    }


    @Provides
    @Singleton
    fun provideToDoRepository(api: ToDoApi, db: AppDataBase): ToDoRepository {
        return TodoRepositoryImplementation(api, db.todoDao())
    }

}