package com.example.androidcomposesample.grid

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.coil.rememberCoilPainter

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GridPage(
  actionToGridDetail: (String) -> Unit
) {
  val names = List(100) { "Grid #$it !" }
  LazyVerticalGrid(
    cells = GridCells.Fixed(2),
    contentPadding = PaddingValues(8.dp)

  ) {
    items(names.size) { index ->
      GridCard(
        label = names[index],
        onItemClick = { actionToGridDetail(index.toString()) }
      )
    }
  }
}

@Composable
fun GridCard(label: String, onItemClick: () -> Unit) {
  Card(
    modifier = Modifier
      .padding(8.dp)
      .fillMaxWidth()
      .border(
        width = 1.dp,
        color = Color.Gray,
        shape = RoundedCornerShape(4.dp)
      )
      .clickable(onClick = onItemClick)
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Image(
        painter = rememberCoilPainter("https://developer.android.com/images/brand/Android_Robot.png"),
        contentDescription = "Android Logo"
      )
      Text(text = label)
    }
  }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview("gridPage")
@Composable
fun PreviewGridPage() {
  GridPage(actionToGridDetail = {})
}
