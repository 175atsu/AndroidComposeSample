package com.example.androidcomposesample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListPage() {
    val names = List(100) { "Hello Android #$it" }
    val countState = remember { mutableStateOf(0) }
    Surface(
        color = Color.Yellow
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            ListItem(names, Modifier.weight(1f))
            Counter2(count = countState.value, updateCount = { count -> countState.value = count })
        }
    }
}

@Composable
fun ListItem(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Text(text = "Hello $name!", modifier = modifier.padding(24.dp))
            Divider(color = Color.Black)
        }
    }
}

@Preview("ListPage")
@Composable
fun PreviewListPage() {
    ListPage()
}