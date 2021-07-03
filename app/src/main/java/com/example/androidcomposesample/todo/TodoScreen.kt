package com.example.androidcomposesample.todo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androidcomposesample.R

@Composable
fun TodoActivityScreen(
  viewModel: TodoViewModel
) {
  val items: List<TodoItem> by viewModel.todoItems.observeAsState(listOf())
  TodoScreen(
    items,
    onAddItem = { viewModel.addItem(it) },
    onRemoveItem = { viewModel.removeItem(it) }
  )
}

@Composable
fun TodoScreen(
  items: List<TodoItem>,
  onAddItem: (TodoItem) -> Unit,
  onRemoveItem: (TodoItem) -> Unit
) {
  Column {
    TodoItemInputBackground(
      elevate = true,
      modifier = Modifier.fillMaxWidth()
    ) {
      TodoItemInput2(onAddItem)
    }
    LazyColumn(
      modifier = Modifier.weight(1f),
      contentPadding = PaddingValues(top = 8.dp)
    ) {
      items(items = items) {
        TodoRow(
          todo = it,
          onItemClicked = { onRemoveItem(it) },
          modifier = Modifier.fillParentMaxWidth()
        )
      }
    }
    Button(
      onClick = { onAddItem(generateRandomTodoItem()) },
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
      Text(stringResource(R.string.todo_random_button))
    }
  }
}

@Composable
fun TodoInputTextField(text: String, onTextChange: (String) -> Unit, modifier: Modifier) {
//  val (text, setText) = remember { mutableStateOf("") }
  TodoInputText(text, onTextChange, modifier)
}

@Composable
fun TodoItemInput2(onItemComplete: (TodoItem) -> Unit) {
  val (text, setText) = remember { mutableStateOf("") }
  val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }
  val iconsVisible = text.isNotBlank()
  val submit = {
    onItemComplete(TodoItem(text, icon))
    setIcon(TodoIcon.Default)
    setText("")
  }

  Column {
    Row {
      TodoInputText(
        text = text,
        onTextChange = setText,
        Modifier
          .weight(1f)
          .padding(end = 8.dp),
        onImeAction = submit
      )
      TodoEditButton(
        onClick = submit,
        modifier = Modifier.align(Alignment.CenterVertically),
        enabled = text.isNotBlank()
      )
    }
    if (iconsVisible) {
      AnimatedIconRow(icon, setIcon, Modifier.padding(top = 8.dp))
    } else {
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}


@Composable
fun TodoItemInput(
  onItemComplete: (TodoItem) -> Unit,
  iconsVisible: Boolean
) {
  val (text, onTextChange) = rememberSaveable { mutableStateOf("") }
  val (icon, onIconChange) = remember { mutableStateOf(TodoIcon.Default) }

  val submit = {
    if (text.isNotBlank()) {
      onItemComplete(TodoItem(text, icon))
      onTextChange("")
      onIconChange(TodoIcon.Default)
    }
  }
  Column {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
    ) {
      TodoInputText(text = text, onTextChange = onTextChange)
      TodoEditButton(onClick = submit, enabled = text.isNotBlank())
    }
    if (text.isNotBlank()) {
      AnimatedIconRow(
        icon = icon,
        onIconChange = onIconChange,
        modifier = Modifier.padding(top = 8.dp),
      )
    } else {
      Spacer(Modifier.height(16.dp))
    }
  }
}