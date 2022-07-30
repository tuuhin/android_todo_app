package com.example.android_todo_app.presentation.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavigation() {
    BottomAppBar(
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
       BottomNavigationItem(
           selected = false,
           onClick = {  },
           icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "add ") },
           label = {Text(text = "none")
           }
       )
    }
}