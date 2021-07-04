package com.example.androidcomposesample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Playground(
  actionToBasics: () -> Unit,
  actionToList: () -> Unit,
  actionToGrid: () -> Unit,
  actionToTodo: () -> Unit,
  actionToTheme: () -> Unit,
  actionToInstagram: () -> Unit,
) {
  val typography = MaterialTheme.typography
  MaterialTheme {
    Column {
      Text(
        text = stringResource(R.string.playground),
        style = typography.h3,
        modifier = Modifier.padding(horizontal = 8.dp)
      )
      TextButton(
        onClick = actionToBasics,
        colors = ButtonDefaults.buttonColors(Color.DarkGray),
        modifier = Modifier.padding(8.dp),
        elevation = ButtonDefaults.elevation()
      ) {
        Text(
          stringResource(R.string.basics_page),
          style = typography.body1,
          color = Color.White
        )
      }
      ToPageButton(
        actionToList,
        R.string.list_page,
        Modifier.padding(8.dp)
      )
      ToPageButton(
        actionToGrid,
        R.string.grid_page,
        Modifier.padding(8.dp)
      )
      ToPageButton(
        actionToTodo,
        R.string.todo_page,
        Modifier.padding(8.dp)
      )
      ToPageButton(
        actionToTheme,
        R.string.theme_page,
        Modifier.padding(8.dp)
      )
      ToPageButton(
        actionToInstagram,
        R.string.instagram_page,
        Modifier.padding(8.dp)
      )
    }
  }
}

@Composable
fun ToPageButton(
  toPage: () -> Unit,
  textResource: Int,
  modifier: Modifier = Modifier
) {
  val typography = MaterialTheme.typography
  Button(
    onClick = toPage,
    colors = ButtonDefaults.buttonColors(Color.DarkGray),
    modifier = modifier
  ) {
    Text(
      text = stringResource(textResource),
      style = typography.body1,
      color = Color.White
    )
  }
}

@Preview(name = "Playground")
@Composable
private fun PlaygroundPreview() {
  Playground(
    actionToBasics = { },
    actionToList = { },
    actionToGrid = { },
    actionToTodo = { },
    actionToTheme = { },
    actionToInstagram = { }
  )
}
