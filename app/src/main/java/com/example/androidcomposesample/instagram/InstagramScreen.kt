package com.example.androidcomposesample.instagram

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun InstagramScreen() {
  val postList = postList
  val baseModifier = Modifier.fillMaxWidth()
  Column {
    InstagramHeader(baseModifier)
    LazyColumn {
      item { StoryRow() }
      item {
        Divider(
          color = Color.Gray.copy(alpha = 0.2f)
        )
      }
      items(postList) { PostInfo(it) }
    }
  }
}

@Composable
fun InstagramHeader(
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .padding(8.dp, 4.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(
      painter = painterResource(R.drawable.instagram_logo),
      contentDescription = null,
      modifier = Modifier
        .width(128.dp),
      contentScale = ContentScale.Fit,
    )
    Spacer(modifier = Modifier.weight(1f))
    Icon(
      painter = painterResource(R.drawable.ic_chat_bubble_outline_black_26dp),
      contentDescription = null,
      modifier = Modifier
        .padding(end = 16.dp)
    )
    Icon(
      painter = painterResource(R.drawable.ic_favorite_border_black_26dp),
      contentDescription = null,
      modifier = Modifier
        .padding(end = 16.dp)
    )
    Icon(
      painter = painterResource(R.drawable.ic_near_me_black_26dp),
      contentDescription = null,
    )
  }
}

@Composable
fun StoryRow() {
  val userList = dummyUser()
  LazyRow {
    userList.forEach {
      item { UseIcon(user = it, modifier = Modifier.padding(8.dp, 4.dp)) }
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

@OptIn(ExperimentalPagerApi::class, InternalCoroutinesApi::class)
@Composable
fun PostInfo(
  post: InstagramPost
) {
  val pagerState = rememberPagerState(pageCount = 5)
  Column {
    PostHeader(post.user)
    PostImage(post.postImage, pagerState)
    Box(
      modifier = Modifier
        .fillMaxWidth(),
      contentAlignment = Alignment.Center
    ) {
      PostIcons()
      Indicators(pagerState)
    }
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
    modifier = modifier.padding(8.dp, 4.dp)
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
        .width(32.dp)
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

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostImage(
  imageList: List<Int>,
  pagerState: PagerState
) {
  Box {
    HorizontalPager(
      state = pagerState,
      modifier = Modifier.fillMaxWidth(),
    ) { page ->
      val imageNumber = page + 1
      Image(
        painter = painterResource(imageList[page]),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxWidth()
      )
    }
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      horizontalArrangement = Arrangement.End,
    ) {
      Text(
        text = "${pagerState.currentPage + 1}/${imageList.size}",
        modifier = Modifier
          .padding(16.dp)
          .background(
            Color.DarkGray.copy(alpha = 0.7f),
            shape = CircleShape,
          )
          .padding(8.dp, 4.dp),
        color = Color.White,
        fontSize = 12.sp
      )
    }
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostIcons(
  modifier: Modifier = Modifier
) {
  Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
      .padding(8.dp)
  ) {
    Icon(
      painter = painterResource(R.drawable.ic_favorite_border_black_26dp),
      contentDescription = null
    )
    Icon(
      painter = painterResource(R.drawable.ic_chat_bubble_outline_black_26dp),
      contentDescription = null,
      modifier = Modifier
        .padding(start = 8.dp)
    )
    Icon(
      painter = painterResource(R.drawable.ic_near_me_black_26dp),
      contentDescription = null,
      modifier = Modifier
        .padding(start = 8.dp)
    )
    Spacer(modifier = Modifier.weight(1f))
    Icon(
      painter = painterResource(R.drawable.ic_bookmark_border_black_26dp),
      contentDescription = null,
      modifier = Modifier
    )
  }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Indicators(
  pagerState: PagerState,
) {
  HorizontalPagerIndicator(
    pagerState = pagerState,
    modifier = Modifier
      .padding(16.dp),
    indicatorWidth = 6.dp,
    activeColor = Color(35, 149, 246),
    inactiveColor = Color.Gray
  )
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
