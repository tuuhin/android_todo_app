package com.example.android_todo_app.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
        DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")
    }
    var selected by remember {
        mutableStateOf(isCompleted)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(10),
        elevation = 10.dp,
        border = BorderStroke(1.dp, Brush.linearGradient(
            colors = listOf(Color.Black,Color.White)
        )),
        backgroundColor = MaterialTheme.colors.surface
    ) {
       Row(
           modifier = Modifier
               .fillMaxWidth()
               .padding(horizontal = 20.dp, vertical = 10.dp),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.Top
       ) {
           Column{
               Text(title, style = MaterialTheme.typography.h5)
               Spacer(modifier = Modifier.height(5.dp))
               description?.let {
                   Text(
                       description,
                       style = MaterialTheme.typography.caption.copy(Color.DarkGray)
                   )
               }
               Text(
                   style = MaterialTheme.typography.button,
                   text = date.format(formatter),
               )
           }
         RadioButton(selected = selected, onClick = {
             selected = !selected
         })
       }
    }
}