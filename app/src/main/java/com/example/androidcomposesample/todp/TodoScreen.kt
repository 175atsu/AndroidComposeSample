package com.example.androidcomposesample.todp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import com.example.androidcomposesample.R

@Composable
fun TodoScreen() {
    val viewModel = TodoViewModel()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (topLabel, todoItem, randomButton) = createRefs()
        val enableTopSection = true
        TodoItemInputBackground(
            elevate = enableTopSection,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topLabel) {
                    top.linkTo(parent.top,44.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }) {
            if (enableTopSection) {
                TodoItemInput({}, false)
            } else {
                Text(
                    text = "Editing item",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(top = 8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(todoItem) {
                    top.linkTo(topLabel.bottom)
                }
        ) {
            items(items = viewModel.todoItems) { todo ->
                TodoRow(todo)
            }
        }
        Button(
            onClick = { viewModel.addItem(generateRandomTodoItem()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(randomButton) {
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                }) {
            Text(stringResource(R.string.todo_random_button))
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

@Preview
@Composable
fun PreviewScreen() {
    TodoScreen()
}

@Preview
@Composable
fun PreviewTodoItemInput() {
    TodoItemInput({}, true)
}