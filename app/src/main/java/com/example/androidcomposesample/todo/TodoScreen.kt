package com.example.androidcomposesample.todo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androidcomposesample.R

@Composable
fun TodoActivityScreen(
  viewModel: TodoViewModel
) {
  TodoScreen(
    items = viewModel.todoItems,
    currentlyEditing = viewModel.currentEditItem,
    onAddItem = viewModel::addItem,
    onRemoveItem = { viewModel.removeItem(it) },
    onStartEdit = viewModel::onEditItemSelected,
    onEditItemChange = viewModel::onEditItemChange,
    onEditDone = viewModel::onEditDone
  )
}

@Composable
fun TodoScreen(
  items: List<TodoItem>,
  currentlyEditing: TodoItem?,
  onAddItem: (TodoItem) -> Unit,
  onRemoveItem: (TodoItem) -> Unit,
  onStartEdit: (TodoItem) -> Unit,
  onEditItemChange: (TodoItem) -> Unit,
  onEditDone: () -> Unit
) {
  Column {
    val enableTopSection = currentlyEditing == null
    TodoItemInputBackground(
      elevate = enableTopSection,
      modifier = Modifier.fillMaxWidth()
    ) {
      if (enableTopSection) {
        TodoItemEntryInput(onAddItem)
      } else {
        Text(
          text = stringResource(R.string.todo_editing_item),
          style = MaterialTheme.typography.h6,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(16.dp)
            .fillMaxWidth()
        )
      }
    }
    LazyColumn(
      modifier = Modifier.weight(1f),
      contentPadding = PaddingValues(top = 8.dp)
    ) {
      items(items = items) { todo ->
        if (currentlyEditing?.id == todo.id) {
          TodoItemInlineEditor(
            item = todo,
            onEditItemChange = onEditItemChange,
            onEditDone = onEditDone,
            onRemoveItem = { onRemoveItem(it) }
          )
        } else {
          TodoRow(
            todo = todo,
            onItemClicked = { onStartEdit(it) },
            modifier = Modifier.fillParentMaxWidth()
          )
        }
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
fun TodoItemEntryInput(onItemComplete: (TodoItem) -> Unit) {
  val (text, setText) = remember { mutableStateOf("") }
  val (icon, setIcon) = remember { mutableStateOf(TodoIcon.Default) }
  val iconsVisible = text.isNotBlank()
  val submit = {
    onItemComplete(TodoItem(text, icon))
    setIcon(TodoIcon.Default)
    setText("")
  }
  TodoItemInput(text, setText, icon, setIcon, submit, iconsVisible)
}

@Composable
fun TodoItemInlineEditor(
  item: TodoItem,
  onEditItemChange: (TodoItem) -> Unit,
  onEditDone: () -> Unit,
  onRemoveItem: (TodoItem) -> Unit
) = TodoItemInput(
  text = item.task,
  onTextChange = { onEditItemChange(item.copy(task = it)) },
  icon = item.icon,
  onIconChange = { onEditItemChange(item.copy(icon = it)) },
  submit = onEditDone,
  iconsVisible = true
)

@Composable
fun TodoItemInput(
  text: String,
  onTextChange: (String) -> Unit,
  icon: TodoIcon,
  onIconChange: (TodoIcon) -> Unit,
  submit: () -> Unit,
  iconsVisible: Boolean
) {
  Column {
    Row {
      TodoInputText(
        text = text,
        onTextChange = onTextChange,
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
      AnimatedIconRow(icon, onIconChange, Modifier.padding(top = 8.dp))
    } else {
      Spacer(modifier = Modifier.height(16.dp))
    }
  }
}