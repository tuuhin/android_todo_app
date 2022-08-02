package com.example.android_todo_app.presentation.screens

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.android_todo_app.presentation.navigation.BottomNavigation
import com.example.android_todo_app.presentation.navigation.CompletedTodoTab
import com.example.android_todo_app.presentation.navigation.IncompleteTodoTab
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AllToDoRoute(
    navController: NavController
) {
    val pagerState = rememberPagerState(pageCount = 2)
    Scaffold(
        bottomBar = { BottomNavigation(pagerState) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.CreateToDo.route)
            }, shape = RoundedCornerShape(50),
                backgroundColor = MaterialTheme.colors.primary,

                ) {
                Icon(Icons.Filled.Add,"add")
            }
        },

        floatingActionButtonPosition = FabPosition.End

    )
    {
        HorizontalPager(state = pagerState) { page ->
            when(page){
                0 -> CompletedTodoTab()
                1 -> IncompleteTodoTab()
            }
        }
    }
}