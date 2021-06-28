package com.example.androidcomposesample

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter
import kotlinx.coroutines.launch

@Composable
fun ListPage() {
  val listSize = 100
  val names = List(listSize) { "Hello Android #$it !" }
  val countState = remember { mutableStateOf(0) }
  val scrollState = rememberLazyListState()

  Surface(
    color = Color.Yellow
  ) {
    Column(
      modifier = Modifier.fillMaxHeight()
    ) {
      ControlButton(scrollState, listSize)
      ListItem(scrollState, names, Modifier.weight(1f))
      Counter2(count = countState.value, updateCount = { count -> countState.value = count })
    }
  }
}

@Composable
fun ListItem(scrollState: LazyListState, names: List<String>, modifier: Modifier = Modifier) {
  LazyColumn(
    modifier = modifier,
    state = scrollState
  ) {
    items(items = names) { name ->
      val isSelected = remember { mutableStateOf(false) }
      val backgroundColor =
        animateColorAsState(targetValue = if (isSelected.value) Color.Red else Color.Transparent)
      Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
          painter = rememberCoilPainter("https://developer.android.com/images/brand/Android_Robot.png"),
          contentDescription = "Android Logo"
        )
        Text(
          text = name,
          modifier = modifier
            .padding(24.dp)
            .background(color = backgroundColor.value)
            .clickable { isSelected.value = !isSelected.value }
        )
      }
      Divider(color = Color.Black)
    }
  }
}

@Composable
fun ControlButton(scrollState: LazyListState, listSize: Int) {
  val coroutineScope = rememberCoroutineScope()
  Row(
    modifier = Modifier.fillMaxWidth()
  ) {
    ScrollButton(
      Modifier.weight(1f),
      ButtonDefaults.buttonColors(Color.White),
      "Scroll to the top"
    ) {
      coroutineScope.launch {
        scrollState.animateScrollToItem(0)
      }
    }
    ScrollButton(
      Modifier.weight(1f),
      ButtonDefaults.buttonColors(Color.Red),
      "Scroll to the end"
    ) {
      coroutineScope.launch {
        scrollState.animateScrollToItem(listSize - 1)
      }
    }
  }
}

@Composable
fun ScrollButton(
  modifier: Modifier = Modifier,
  color: ButtonColors,
  label: String,
  click: () -> Unit
) {
  Button(
    onClick = click,
    modifier = modifier.padding(8.dp),
    colors = color,
  ) {
    Text(
      text = label,
      style = typography.subtitle1,
    )
  }
}

@Preview("ListPage")
@Composable
fun PreviewListPage() {
  ListPage()
}