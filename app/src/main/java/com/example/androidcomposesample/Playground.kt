package com.example.androidcomposesample

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Playground(actionToList: () -> Unit) {
    MaterialTheme {
        Column {
            TextButton(onClick = actionToList) {
                Text("ListPage")
            }
            TextButton(onClick = { /*TODO*/ }) {
            }
        }
    }
}

@Preview(name = "Playground")
@Composable
private fun PlaygroundPreview() {
    Playground(actionToList = { })
}