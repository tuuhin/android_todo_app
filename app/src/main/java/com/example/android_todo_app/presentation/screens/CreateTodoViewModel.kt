package com.example.android_todo_app.presentation.screens


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo_app.domain.Resource
import com.example.android_todo_app.domain.models.CreateTodoModel
import com.example.android_todo_app.domain.models.ToDoModel
import com.example.android_todo_app.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CreateTodoViewModel @Inject constructor(
    private val todoRepository: ToDoRepository
) : ViewModel() {

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    private val _desc = mutableStateOf("")
    val desc: State<String> = _desc

    private val _mark = mutableStateOf(false)
    val mark: State<Boolean> = _mark

    private val _todoState = MutableSharedFlow<String>()

    val todoFlow = _todoState.asSharedFlow()

    fun toggleMark() {
        _mark.value = !_mark.value
    }

    fun onTitleChange(str: String) {
        _title.value = str
    }

    fun onDescChange(str: String) {
        _desc.value = str
    }

    fun addToDo() {
        viewModelScope.launch {
            val createTodo = CreateTodoModel(
                title = _title.value,
                desc = _desc.value,
                isCompleted = _mark.value
            )
            when (val create: Resource<ToDoModel> = todoRepository.createTodo(createTodo)) {
                is Resource.Success -> {
                    create.data?.let { _todoState.emit("${it.title} created ") }
                }
                is Resource.Loading -> {
                    _todoState.emit("Adding your todo")

                }
                is Resource.Error -> {
                    create.message?.let { _todoState.emit(it) }
                }
            }
        }
    }
}