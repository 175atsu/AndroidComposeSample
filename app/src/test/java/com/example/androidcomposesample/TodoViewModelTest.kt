package com.example.androidcomposesample

import com.example.androidcomposesample.todo.TodoViewModel
import com.example.androidcomposesample.todo.generateRandomTodoItem
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class TodoViewModelTest {

  @Test
  fun testRemoveItem() {
    val viewModel = TodoViewModel()
    val item1 = generateRandomTodoItem()
    val item2 = generateRandomTodoItem()
    viewModel.addItem(item1)
    viewModel.addItem(item2)

    viewModel.removeItem(item1)

    assertThat(viewModel.todoItems).isEqualTo(listOf(item2))
  }
}