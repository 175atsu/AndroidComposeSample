package com.example.androidcomposesample.tiktok

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.transform.CircleCropTransformation
import com.example.androidcomposesample.R
import com.example.androidcomposesample.tiktok.data.TikTokPost
import com.example.androidcomposesample.tiktok.data.postList
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TikTokScreen() {
  val pagerState = rememberPagerState(pageCount = postList.size)
  val postInfo = postList
  VerticalPager(
    state = pagerState,
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.End,
    verticalAlignment = Alignment.Bottom
  ) { page ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .clip(
          RoundedCornerShape(4.dp)
        )
    ) {
      VideoComponent(postInfo[page].movieUrl)
      VideoOverLayUI(postInfo[page])
    }
  }
}

@Composable
fun VideoComponent(uri: String) {
  val context = LocalContext.current
  val tiktokPlayer = remember {
    SimpleExoPlayer.Builder(context)
      .build()
      .apply {
        val mediaSource = ProgressiveMediaSource.Factory(
          DefaultDataSourceFactory(context, "exoplayer-sample-app")
        )
          .createMediaSource(MediaItem.fromUri("asset:///$uri"))
        playWhenReady = true
        repeatMode = Player.REPEAT_MODE_ONE
        setMediaSource(mediaSource)
        prepare()
      }
  }

  AndroidView(
    {
      PlayerView(it).apply {
        setBackgroundColor(resources.getColor(R.color.tiktok_background))
        useController = false
        player = tiktokPlayer
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
      }
    },
    modifier = Modifier
      .fillMaxHeight(1f)
  )
}

@Composable
fun VideoOverLayUI(
  post: TikTokPost
) {
  Row(
    modifier = Modifier
      .fillMaxSize()
      .padding(8.dp),
    verticalAlignment = Alignment.Bottom
  ) {
    VideoInfoSection(
      post = post,
      modifier = Modifier
        .weight(1f)
        .padding(end = 40.dp)
    )
    PostIcons(post)
  }
}

@Composable
fun VideoInfoSection(
  post: TikTokPost,
  modifier: Modifier = Modifier
) {
  val state = rememberScrollState()
  LaunchedEffect(Unit) {
    state.animateScrollTo(
      state.maxValue,
      animationSpec = infiniteRepeatable(
        repeatMode = RepeatMode.Restart,
        animation = tween(durationMillis = 3500, easing = LinearEasing),
      ),
    )
  }

  Column(
    modifier = modifier
  ) {
    TikTokInfoTextComponent(
      label = "@${post.user.name}"
    )
    Spacer(Modifier.height(8.dp))
    TikTokInfoTextComponent(
      label = post.content,
    )
    Spacer(Modifier.height(8.dp))
    Row {
      Icon(
        painter = painterResource(R.drawable.ic_music_note_white_24dp),
        contentDescription = null,
        modifier = Modifier.size(24.dp),
        tint = Color.White
      )
      TikTokInfoTextComponent(
        label = post.musicTitle,
        modifier = Modifier
          .horizontalScroll(state = state, enabled = false)
      )
    }
  }
}

@Composable
fun PostIcons(
  post: TikTokPost
) {
  val rotation = remember { Animatable(0f) }
  LaunchedEffect(Unit) {
    rotation.animateTo(
      targetValue = 360f,
      animationSpec = infiniteRepeatable(
        animation = tween(durationMillis = 3500, easing = LinearEasing),
      ),
    )
  }
  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = rememberCoilPainter(
        request = post.user.image,
        requestBuilder = {
          transformations(CircleCropTransformation())
        }
      ),
      contentDescription = null,
      modifier = Modifier
        .width(44.dp)
    )
    Spacer(modifier = Modifier.height(24.dp))
    TikTokIconComponent(
      R.drawable.ic_favorite_white_24dp,
      post.like.toString()
    )
    Spacer(modifier = Modifier.height(24.dp))
    TikTokIconComponent(
      R.drawable.ic_chat_bubble_white_24dp,
      post.comment.toString()
    )
    Spacer(modifier = Modifier.height(24.dp))
    TikTokIconComponent(
      R.drawable.ic_reply_white_24dp,
      post.share.toString()
    )
    Spacer(modifier = Modifier.height(64.dp))
    Image(
      painter = rememberCoilPainter(
        request = post.musicThumbnail,
        requestBuilder = {
          transformations(CircleCropTransformation())
        }
      ),
      contentDescription = null,
      modifier = Modifier
        .width(44.dp)
        .rotate(rotation.value)
    )
  }
}
