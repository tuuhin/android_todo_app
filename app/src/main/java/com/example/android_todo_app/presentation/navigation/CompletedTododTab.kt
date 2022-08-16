package com.example.android_todo_app.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.android_todo_app.R
import com.example.android_todo_app.presentation.component.ToDoCard
import com.example.android_todo_app.presentation.screens.ViewTodoViewModel
import java.time.LocalDateTime
import kotlin.random.Random

@Composable
fun CompletedTodoTab(
    viewModel: ViewTodoViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Column(content = {
            Text(text = stringResource(R.string.app_name))
            Text(
                text = "Scheduled Task",
                style = MaterialTheme.typography.caption.copy(Color.Gray),
                modifier = Modifier.padding(vertical = 10.dp)
            )
            LazyColumn {
                items(300) {
                    ToDoCard(
                        title = "Hello $it",
                        description = if (it % 2 == 0) "tuhin" else null,
                        date = LocalDateTime.now(),
                        isCompleted = Random(45).nextInt() % 4 == 2, onTap = {}
                    )
                }
            }

        })

    }
}