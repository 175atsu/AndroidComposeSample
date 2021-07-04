package com.example.androidcomposesample.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.example.androidcomposesample.R
import com.example.androidcomposesample.instagram.data.InstagramPost
import com.example.androidcomposesample.instagram.data.InstagramUser
import com.example.androidcomposesample.instagram.data.dummyUser
import com.example.androidcomposesample.instagram.data.postList
import com.example.androidcomposesample.theming.Header
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun InstagramScreen() {
  val postList = postList
  Column {
    Header(text = stringResource(R.string.instagram_title))
    LazyColumn {
      item { StoryRow() }
      items(postList) { PostInfo(it) }
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
  user: InstagramUser,
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

@Composable
fun PostInfo(
  post: InstagramPost
) {
  Column {
    PostHeader(post.user)
    PostImage(post.postImage)
    PostIcons()
    PostComment(post)
  }
}

@Composable
fun PostHeader(
  user: InstagramUser,
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier.padding(8.dp)
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
        .width(24.dp)
    )
    Text(
      text = user.name,
      modifier = modifier
        .weight(1f)
        .padding(start = 8.dp)
    )
    IconButton(onClick = { /*TODO*/ }) {
      Icon(
        painter = painterResource(R.drawable.ic_more_vert_black_24dp),
        contentDescription = null
      )
    }
  }
}

@Composable
fun PostImage(
  image: String
) {
  Image(
    painter = painterResource(R.drawable.post_1_thumb),
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = Modifier.fillMaxWidth()
  )
}

@Composable
fun PostIcons() {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
      .fillMaxWidth()
      .padding(8.dp)
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_favorite_border_black_32dp),
      contentDescription = null
    )
    Icon(
      painter = painterResource(R.drawable.ic_chat_bubble_outline_black_32dp),
      contentDescription = null,
      modifier = Modifier
        .padding(start = 8.dp)
    )
    Icon(
      painter = painterResource(R.drawable.ic_near_me_black_32dp),
      contentDescription = null,
      modifier = Modifier
        .padding(start = 8.dp)
    )
    Icon(
      painter = painterResource(R.drawable.ic_near_me_black_32dp),
      contentDescription = null,
      modifier = Modifier
        .padding(start = 8.dp)
        .weight(1f)
    )
    Icon(
      painter = painterResource(R.drawable.ic_bookmark_border_black_32dp),
      contentDescription = null,
      modifier = Modifier
    )
  }
}

@Composable
fun PostComment(
  post: InstagramPost
) {
  Column(
    modifier = Modifier.padding(8.dp, 0.dp)
  ) {
    Text(
      text = stringResource(R.string.instagram_like, post.like)
    )
    Text(
      text = post.user.name + post.content
    )
  }
}
