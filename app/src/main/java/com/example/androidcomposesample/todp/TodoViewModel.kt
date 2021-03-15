package com.example.androidcomposesample.todp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel: ViewModel() {

    var todoItems by mutableStateOf(listOf<TodoItem>())
        private set

    fun addItem(item: TodoItem) {
        todoItems = todoItems + listOf(item)
    }
}