package com.example.android_todo_app.presentation

sealed class UiEvent(val message: String?) {
    class ShowSnackBar(message: String?) : UiEvent(message)
}
