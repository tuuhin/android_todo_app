package com.example.android_todo_app.presentation.screens

import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun CreateToDo(
    navController: NavController,
    viewModel:CreateTodoViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = scaffoldState){
        viewModel.todoFlow.collectLatest {message->
            scaffoldState.snackbarHostState.showSnackbar(message = "$message")
        }
    }
       Scaffold(scaffoldState=scaffoldState) {
           Column(
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.Start,
               modifier = Modifier
                   .fillMaxSize()
                   .padding(25.dp)
           ) {
               Text(text = "Add Task",
                   style = MaterialTheme.typography.h4
               )
               Text(text = "Fill out details to add new tasks ",
                   style = MaterialTheme.typography.caption
                       .copy(fontSize = 16.sp, fontWeight = FontWeight.Light)
               )
               Spacer(modifier = Modifier.height(8.dp))
               TextField(
                     value = viewModel.title.value,
                     onValueChange = viewModel::onTitleChange,
                     label = { Text(text = "Title")},
                     singleLine = true,
                     shape = RoundedCornerShape(20),
                     keyboardOptions = KeyboardOptions(
                         keyboardType = KeyboardType.Text,
                         capitalization = KeyboardCapitalization.Sentences
                     ),
                     modifier = Modifier
                         .fillMaxWidth()
                         .padding(vertical = 5.dp),
                     colors = TextFieldDefaults.textFieldColors(
                         unfocusedIndicatorColor = Color.Transparent,
                         focusedIndicatorColor = Color.Transparent
                     )
                 )
               TextField(
                   value = viewModel.desc.value, onValueChange =viewModel::onDescChange,
                   label = { Text(text = "Description")},
                   singleLine = false,
                   shape = RoundedCornerShape(15),
                   keyboardOptions = KeyboardOptions(
                       keyboardType = KeyboardType.Text,
                       capitalization = KeyboardCapitalization.Sentences
                   ),
                   maxLines = 10,
                   modifier = Modifier
                       .fillMaxWidth()
                       .height(75.dp)
                       .padding(vertical = 5.dp),
                   colors = TextFieldDefaults.textFieldColors(
                       unfocusedIndicatorColor = Color.Transparent,
                       focusedIndicatorColor = Color.Transparent
                   )
               )
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   TextField(
                       value =  LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEE, d MMM yyyy HH:mm")),
                       onValueChange = {},
                       label = { Text("Time")},
                       readOnly = true,
                       singleLine = true,
                       shape = RoundedCornerShape(20),
                       modifier = Modifier
                           .fillMaxWidth()
                           .padding(vertical = 5.dp),
                       colors = TextFieldDefaults.textFieldColors(
                           unfocusedIndicatorColor = Color.Transparent,
                           focusedIndicatorColor = Color.Transparent
                       )
                   )
               }

               Row(
                   horizontalArrangement = Arrangement.SpaceBetween,
                   modifier = Modifier
                       .padding(vertical = 8.dp)
                       .fillMaxWidth()
               ) {
                   Button(
                       onClick = { navController.popBackStack() },
                       contentPadding = PaddingValues(vertical = 15.dp),
                       shape = RoundedCornerShape(20),
                       modifier = Modifier.weight(4f),
                       colors = ButtonDefaults.buttonColors(
                           backgroundColor = Color.Transparent
                       ), border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                       elevation = elevation(defaultElevation = 0.dp, pressedElevation = 5.dp
                       )
                   ) {
                       Text("Cancel",style = MaterialTheme.typography.subtitle1)
                   }
                   Spacer(modifier = Modifier.width(16.dp))
                   Button(
                       onClick =viewModel::addToDo,
                       shape = RoundedCornerShape(20),
                       contentPadding = PaddingValues(vertical = 15.dp),
                       modifier = Modifier.weight(4f)
                   ) {
                       Text("Add", style = MaterialTheme.typography.subtitle1)
                   }
               }
           }
       }
}