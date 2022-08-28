package com.example.android_todo_app.presentation.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_todo_app.R
import com.example.android_todo_app.presentation.component.ToDoCard
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun IncompleteTodoTab(
    viewModel: IncompleteTodoViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = scaffoldState) {

        viewModel.eventFlow.collectLatest { event ->
            if (!event.message.isNullOrBlank()) {
                scaffoldState.snackbarHostState.showSnackbar(event.message)
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.padding(10.dp)
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {

            Text(text = "Incomplete Todos", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))

            if (viewModel.todoState.value.isLoading) {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            } else if (viewModel.todoState.value.todos.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painterResource(R.drawable.inbox),
                        "No item Present",
                        modifier = Modifier.align(Alignment.Center)
                    )

                }
            }
            SwipeRefresh(
                state = rememberSwipeRefreshState(viewModel.refreshing.value),
                onRefresh = viewModel::refresh,
                indicator = { state, trigger ->
                    SwipeRefreshIndicator(
                        state = state,
                        refreshTriggerDistance = trigger,
                        scale = true,
                        contentColor =  MaterialTheme.colors.primary
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(viewModel.todoState.value.todos.size) { idx ->
                        val todo = viewModel.todoState.value.todos[idx]
                        ToDoCard(
                            title = todo.title,
                            description = todo.desc,
                            date = todo.createdAt,
                            isCompleted = todo.isCompleted,
                            onTap = { viewModel.updateTodo(todo) },
                            onDelete = { viewModel.deleteTodo(todo) },
                            interactive = true

                        )
                    }

                }
            }
        }


    }
}