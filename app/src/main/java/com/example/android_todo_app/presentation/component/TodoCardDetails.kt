package com.example.android_todo_app.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun TodoCardDetails(
    title: String,
    description: String?,
    date: LocalDateTime,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(10.dp)
    ) {
        Text(
            title,
            style = MaterialTheme.typography.h6,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Spacer(
            modifier = Modifier
                .height(2.dp)
        )
        description?.let { str ->
            Text(
                str,
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Text(
            style = MaterialTheme.typography.subtitle2,
            text = date.format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")),
        )
    }
}