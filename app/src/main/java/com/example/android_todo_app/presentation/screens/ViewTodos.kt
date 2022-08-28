package com.example.android_todo_app.presentation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.android_todo_app.presentation.navigation.AllTodoTab
import com.example.android_todo_app.presentation.navigation.BottomNavigation
import com.example.android_todo_app.presentation.navigation.CompletedTodoTab
import com.example.android_todo_app.presentation.navigation.IncompleteTodoTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewTodos(
    navController: NavController,
) {
    val pagerState = rememberPagerState(pageCount = 3)
    Scaffold(
        bottomBar = { BottomNavigation(pagerState) },
        topBar = {
            TopAppBar(title = { Text(text = "Your Todos") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Routes.CreateToDo.route)
                },
                shape = MaterialTheme.shapes.medium,
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item Route")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )
    {
        HorizontalPager(state = pagerState, modifier = Modifier.padding(it)) { page ->
            when (page) {
                0 -> AllTodoTab()
                1 -> CompletedTodoTab()
                2 -> IncompleteTodoTab()
            }
        }
    }
}