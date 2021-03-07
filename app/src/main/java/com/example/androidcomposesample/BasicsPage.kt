package com.example.androidcomposesample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BasicsPage() {
    val image = painterResource(R.drawable.header)
    val typography = MaterialTheme.typography
    val counterState = remember { mutableStateOf(0) }
    val imageModifier = Modifier
        .requiredHeight(180.dp)
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(4.dp))
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.basics_page),
            style = typography.h2
        )
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
        Spacer(Modifier.height(16.dp))
        Surface(
            color = Color.Yellow
        ) {
            Text(
                "A day wandering through the sandhills" +
                        "in Shark Fin Cove, and a few of the " +
                        "sights I saw",
                style = typography.h5,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(8.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Counter()
            Counter2(
                count = counterState.value,
                updateCount = {
                    counterState.value = it
                }
            )
        }
    }
}

@Composable
fun Counter() {
    val count = remember { mutableStateOf(0) }
    Button(onClick = { count.value++ }) {
        Text("I've been clicked ${count.value} times")
    }
}

@Composable
fun Counter2(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (count > 5) Color.Green else Color.White)
    ) {
        Text("I've been clicked $count times")
    }
}

@Preview("ComposeBasics")
@Composable
fun PreviewBasicsPage() {
    BasicsPage()
}