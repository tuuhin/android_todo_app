package com.example.android_todo_app.presentation.component

import android.os.Build
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun ToDoCard(
    title:String,
    description:String?,
    date: LocalDateTime,
    isCompleted:Boolean
){
    val formatter = remember {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")
        } else {
            TODO("VERSION.SDK_INT < O")
        }
    }
    var selected by remember {
        mutableStateOf(isCompleted)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { selected = !selected },
        shape = MaterialTheme.shapes.medium,
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.surface,
        border = BorderStroke(1.dp,MaterialTheme.colors.onSurface)
    ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(10.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.Top
       ) {
           Column{
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