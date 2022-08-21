package com.example.android_todo_app.presentation.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo_app.domain.Resource
import com.example.android_todo_app.domain.models.ToDoModel
import com.example.android_todo_app.domain.repository.ToDoRepository
import com.example.android_todo_app.presentation.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class IncompleteTodoViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository
) : ViewModel() {

    private var _todoState = mutableStateOf(TodoState())

    val todoState: State<TodoState> = _todoState

    private val _uiEvent = MutableSharedFlow<UiEvent>()

    val eventFlow = _uiEvent.asSharedFlow()

    fun updateTodo(todoModel: ToDoModel) {
        viewModelScope.launch {
            when (val updateTodo = toDoRepository.updateTodo(
                todoModel.copy(isCompleted = true)
            )) {
                is Resource.Success ->
                    _uiEvent.emit(UiEvent.ShowSnackBar(message = "updated todo: ${updateTodo.data!!.title}"))
                is Resource.Error ->
                    _uiEvent.emit(UiEvent.ShowSnackBar(updateTodo.message))
                else -> {}
            }
        }
    }

    fun deleteTodo(todoId: Int) {
        viewModelScope.launch {
            when (val isDelete = toDoRepository.deleteTodo(todoId)) {
                is Resource.Success -> _uiEvent.emit(UiEvent.ShowSnackBar(message = "Todo deleted"))
                is Resource.Error -> _uiEvent.emit(UiEvent.ShowSnackBar(isDelete.message))
                else -> {}
            }
        }
    }

    fun loadInCompletedTodos() {
        viewModelScope.launch {
            toDoRepository.getAllInCompletedTodos().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        _todoState.value = todoState.value.copy(
                            todos = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Loading -> {
                        _todoState.value =
                            todoState.value.copy(isLoading = true, todos = emptyList())
                    }

                    is Resource.Error -> {
                        _todoState.value = todoState.value.copy(
                            isLoading = false,
                            todos = result.data ?: emptyList()
                        )
                        _uiEvent.emit(UiEvent.ShowSnackBar(result.message))
                    }
                }
            }.launchIn(this)
        }
    }
}