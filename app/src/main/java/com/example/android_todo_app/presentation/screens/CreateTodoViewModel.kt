package com.example.android_todo_app.presentation.screens
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo_app.data.local.AppDataBase
import com.example.android_todo_app.data.remote.ToDoApi
import com.example.android_todo_app.data.repository.TodoRepositoryImplementation
import com.example.android_todo_app.domain.models.ToDoModel
import com.example.android_todo_app.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.io.Closeable
import java.lang.Exception
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject


data class CreateTodoState(
    val message:String?,
    val error:String?
)


@HiltViewModel
class CreateTodoViewModel @Inject constructor(
  private  val todoRepository:ToDoRepository
): ViewModel() {

    private var _title = mutableStateOf("")
    val title:State<String> = _title

    private  val _desc = mutableStateOf("")
    val desc :State<String> = _desc

    private val _todoState = MutableSharedFlow<CreateTodoState>()

    val todoFlow = _todoState.asSharedFlow()

    fun onTitleChange(str:String) {
        _title.value = str
    }

    fun onDescChange(str:String){
        _desc.value = str
    }

    fun addToDo(){


    }

}