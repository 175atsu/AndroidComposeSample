package com.example.androidcomposesample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Playground(
    actionToBasics: () -> Unit,
    actionToList: () -> Unit
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
            Button(
                onClick = actionToList,
                colors = ButtonDefaults.buttonColors(Color.DarkGray),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    stringResource(R.string.list_page),
                    style = typography.body1,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(name = "Playground")
@Composable
private fun PlaygroundPreview() {
    Playground(
        actionToBasics = { },
        actionToList = { }
    )
}