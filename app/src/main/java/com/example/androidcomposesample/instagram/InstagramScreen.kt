package com.example.androidcomposesample.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.example.androidcomposesample.theming.Header
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun InstagramScreen() {
  Column {
    Header(text = "hoge")
    LazyColumn {
      item { StoryRow() }
      item { }
    }
  }
}

@Composable
fun StoryRow() {
  val userList = dummyUser()
  LazyRow {
    userList.forEach {
      item { UseIcon(user = it, modifier = Modifier.padding(8.dp)) }
    }
  }
}

@Composable
fun UseIcon(
  user: User,
  modifier: Modifier = Modifier
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = rememberCoilPainter(
        request = user.image,
        requestBuilder = {
          transformations(CircleCropTransformation())
        }
      ),
      contentDescription = null,
      modifier = Modifier
        .width(64.dp)
    )
    Text(
      text = user.name,
      fontSize = 12.sp,
      textAlign = TextAlign.Center,
      modifier = modifier
        .fillMaxWidth()
    )
  }
}
