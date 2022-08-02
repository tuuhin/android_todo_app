package com.example.android_todo_app.presentation.navigation

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigation(
    pagerState: PagerState
) {
    val scope = rememberCoroutineScope()
    BottomAppBar(
        elevation = 10.dp,
        backgroundColor = MaterialTheme.colors.background
    ) {
       BottomNavigationItem(
           selectedContentColor = MaterialTheme.colors.primary,
           selected = pagerState.currentPage==0,
           onClick = {
               scope.launch {
                   pagerState.animateScrollToPage(0)
               }
           },
           icon = { Icon(imageVector = Icons.Default.PlaylistAdd, contentDescription = "add ") },
           label = { Text(text = "All Todo's")}

       )
        BottomNavigationItem(
            selected = pagerState.currentPage==1,
            selectedContentColor = MaterialTheme.colors.primary,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            },
            icon = { Icon(imageVector = Icons.Default.Schedule, contentDescription = "add ") },
            label = { Text(text = "Incompled Todo's")}
        )
       
    }
}