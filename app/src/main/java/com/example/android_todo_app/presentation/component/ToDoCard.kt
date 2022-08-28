package com.example.android_todo_app.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime

@Composable
fun ToDoCard(
    title: String,
    description: String?,
    date: LocalDateTime,
    isCompleted: Boolean,
    modifier: Modifier = Modifier,
    onTap: () -> Unit = {},
    onDelete: () -> Unit = {},
    enabled: Boolean? = false,
    interactive: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(
                enabled = enabled ?: false,
                onClick = onTap
            ),
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        if (interactive) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TodoCardDetails(
                    title = title,
                    description = description,
                    date = date,
                    modifier = Modifier.weight(0.75f)
                )
                RadioButton(
                    modifier = Modifier.weight(.1f),
                    selected = isCompleted,
                    onClick = onTap
                )
                Spacer(modifier = Modifier.weight(.025f))
                IconButton(
                    modifier = Modifier.weight(.1f),
                    onClick = onDelete
                ) {
                    Icon(
                        Icons.Outlined.Delete,
                        contentDescription = "Delete This Item",
                        tint = MaterialTheme.colors.primary
                    )
                }
                Spacer(modifier = Modifier.weight(.025f))

            }
        } else {
            TodoCardDetails(title = title, description = description, date = date)
        }


    }
}