package com.example.android_todo_app.presentation.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_todo_app.domain.Resource
import com.example.android_todo_app.domain.models.ToDoModel
import com.example.android_todo_app.domain.repository.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TodoState<T>(
    val data: T?, val isLoading: Boolean,
)

@HiltViewModel
class ViewTodoViewModel @Inject constructor(
    private  val todoRepository:ToDoRepository
):ViewModel() {

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    private  val _allTodos = mutableStateOf<List<ToDoModel>>(emptyList())

    val allTodos :State<List<ToDoModel>> = _allTodos

    val eventFlow = _eventFlow.asSharedFlow()

    private fun getToDo(){
        viewModelScope.launch {
            todoRepository.getAllTodos().onEach { result->
                when(result){
                    is Resource.Success ->{
                        _allTodos.value = result.data?: emptyList()
                    }
                    is Resource.Error ->{
                        _eventFlow.emit(UIEvent.ShowSnackbar(result.message?:"Unknown Error Occur"))
                    }
                    is Resource.Loading ->{

                    }
                }
            }.launchIn(this)
        }
    }

    sealed class UIEvent{
        data class ShowSnackbar(val message:String):UIEvent()
    }
}