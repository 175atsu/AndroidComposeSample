package com.example.androidcomposesample.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {

//    var todoItems by mutableStateOf(listOf<TodoItem>())
//        private set

  private var _todoItems = MutableLiveData(listOf<TodoItem>())
  val todoItems: LiveData<List<TodoItem>> = _todoItems

  fun addItem(item: TodoItem) {
//        todoItems = todoItems + listOf(item)
    _todoItems.value = _todoItems.value!! + listOf(item)
  }

  fun removeItem(item: TodoItem) {
    _todoItems.value = _todoItems.value!!.toMutableList().also {
      it.remove(item)
    }
  }
}