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

    private val _isRefreshing = mutableStateOf(false)

    val refreshing: State<Boolean> = _isRefreshing

    init {
        loadInCompletedTodos()
    }

    fun refresh(){
        loadInCompletedTodos()
    }

    fun updateTodo(todoModel: ToDoModel) {
        viewModelScope.launch {
            when (val updateTodo = toDoRepository.updateTodo(
                todoModel.copy(isCompleted = true)
            )) {
                is Resource.Success -> {
                    _uiEvent.emit(UiEvent.ShowSnackBar(message = "updated todo: ${updateTodo.data!!.title}"))
                    _todoState.value =
                        _todoState.value.copy(todos = _todoState.value.todos.filter { toDo ->
                            toDo.id != todoModel.id
                        })
                }
                is Resource.Error ->
                    _uiEvent.emit(UiEvent.ShowSnackBar(updateTodo.message))
                else -> {}
            }
        }
    }

    fun deleteTodo(todo: ToDoModel) {
        viewModelScope.launch {
            when (val isDelete = toDoRepository.deleteTodo(todo)) {
                is Resource.Success -> {
                    _uiEvent.emit(UiEvent.ShowSnackBar(message = "Todo deleted"))
                    _todoState.value =
                        _todoState.value.copy(todos = _todoState.value.todos.filter { toDo ->
                            toDo.id != todo.id
                        })
//
                }
                is Resource.Error -> _uiEvent.emit(UiEvent.ShowSnackBar(isDelete.message))
                else -> {}
            }
        }
    }

    private fun loadInCompletedTodos() {
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