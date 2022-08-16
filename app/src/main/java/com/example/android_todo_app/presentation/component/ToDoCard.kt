package com.example.android_todo_app.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun ToDoCard(
    title: String,
    description: String?,
    date: LocalDateTime,
    isCompleted: Boolean,
    enabled: Boolean? = false,
    onTap: () -> Unit
) {
    val formatter = remember {
        DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")
    }
    var selected by remember {
        mutableStateOf(isCompleted)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(
                enabled = enabled ?: false,
                onClick = onTap
            ),
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(1.dp, MaterialTheme.colors.onSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    title,
                    style = MaterialTheme.typography.h6,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(2.dp))
                description?.let {
                    Text(
                        description,
                        style = MaterialTheme.typography.caption,
                        fontStyle = FontStyle.Italic
                    )
                }
                Text(
                    style = MaterialTheme.typography.subtitle2,
                    text = date.format(formatter),
                )
            }
            RadioButton(
                selected = selected,
                onClick = { selected = !selected }
            )
        }
    }
}