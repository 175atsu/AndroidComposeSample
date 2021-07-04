package com.example.androidcomposesample.thema

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ListItem
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.example.androidcomposesample.R
import java.util.*

@Composable
fun ThemeScreen() {
  val featured = remember { PostRepo.getFeaturedPost() }
  val posts = remember { PostRepo.getPosts() }
  MaterialTheme {
    LazyColumn {
      item { Header(stringResource(R.string.theme_top_story)) }
      item { FeaturedPost(post = featured, modifier = Modifier.padding(16.dp)) }
      item { Header(stringResource(R.string.theme_popular)) }
      items(posts) { post ->
        PostItem(post)
        Divider(startIndent = 72.dp)
      }
    }
  }
}

@Composable
fun Header(
  text: String,
  modifier: Modifier = Modifier
) {
  Text(
    text = text,
    modifier = modifier
      .fillMaxWidth()
      .background(Color.LightGray)
      .padding(16.dp, 8.dp)
  )
}

@Composable
fun FeaturedPost(
  post: Post,
  modifier: Modifier = Modifier
) {
  Card(
    modifier = modifier
  ) {
    Column(
      modifier = Modifier
        .fillMaxWidth()
        .clickable { /* onClick */ }
    ) {
      Image(
        painter = painterResource(post.imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
          .heightIn(min = 180.dp)
          .fillMaxWidth()
      )
      Spacer(Modifier.height(16.dp))
      val padding = Modifier.padding(horizontal = 16.dp)
      Text(
        text = post.title,
        modifier = padding
      )
      Text(
        text = post.metadata.author.name,
        modifier = padding
      )
      PostMetadata(
        post = post,
        modifier = padding
      )
      Spacer(Modifier.height(16.dp))
    }
  }
}

@Composable
fun PostMetadata(
  post: Post,
  modifier: Modifier = Modifier
) {
  val divider = "  •  "
  val tagDivider = "  "
  val text = buildAnnotatedString {
    append(post.metadata.date)
    append(divider)
    append(stringResource(R.string.theme_read_time, post.metadata.readTimeMinutes))
    append(divider)
    post.tags.forEachIndexed { index, tag ->
      if (index != 0) {
        append(tagDivider)
      }
      append(" ${tag.uppercase(Locale.getDefault())} ")
    }
  }
  Text(
    text = text,
    modifier = modifier
  )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostItem(
  post: Post,
  modifier: Modifier = Modifier
) {
  ListItem(
    modifier = modifier
      .clickable { /* todo */ }
      .padding(vertical = 8.dp),
    icon = {
      Image(painter = painterResource(post.imageThumbId), contentDescription = null)
    },
    text = { Text(text = post.title) },
    secondaryText = { PostMetadata(post) }
  )
}
