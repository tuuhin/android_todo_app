package com.example.android_todo_app.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest


@Composable
fun CreateToDo(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CreateTodoViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = scaffoldState) {
        viewModel.todoFlow.collectLatest { message ->
            scaffoldState.snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add To do ", textAlign = TextAlign.Center)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Return Back"
                        )
                    }
                },
            )
        }

    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxSize()
                .padding(25.dp)
        ) {
            Text(
                text = "Add Task",
                style = MaterialTheme.typography.h4
            )
            Text(
                text = "Fill out details to add new tasks ",
                style = MaterialTheme.typography.caption
                    .copy(fontSize = 16.sp, fontWeight = FontWeight.Light)
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.title.value,
                onValueChange = viewModel::onTitleChange,
                label = { Text(text = "Title") },
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
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
                value = viewModel.desc.value,
                onValueChange = viewModel::onDescChange,
                label = { Text(text = "Description") },
                singleLine = false,
                shape = MaterialTheme.shapes.medium,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences
                ),
                maxLines = 10,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.3f)
                    .padding(vertical = 5.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                )
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(text = "Mark as Completed")
                RadioButton(
                    selected = viewModel.mark.value,
                    onClick = viewModel::toggleMark
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Button(
                    contentPadding = PaddingValues(10.dp),
                    onClick = { navController.popBackStack() },
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.weight(.4f),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Transparent
                    ), border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                    elevation = elevation(
                        defaultElevation = 0.dp, pressedElevation = 5.dp
                    )
                ) {
                    Text("Cancel", style = MaterialTheme.typography.subtitle1)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = viewModel::addToDo,
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.weight(.4f),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    Text("Add", style = MaterialTheme.typography.subtitle1)
                }
            }
        }
    }
}