package com.example.android_todo_app.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.android_todo_app.presentation.navigation.BottomNavigation

@Composable
fun AllToDoRoute(
    navController: NavController
) {
    Scaffold(
        bottomBar = { BottomNavigation() },
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
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "All")
        }
    }
}