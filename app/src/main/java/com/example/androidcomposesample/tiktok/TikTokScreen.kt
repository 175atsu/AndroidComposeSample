package com.example.androidcomposesample.tiktok

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TikTokScreen(context: Context) {
  val pagerState = rememberPagerState(pageCount = 3)
  val postInfo = postList
  VerticalPager(
    state = pagerState,
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.End,
    verticalAlignment = Alignment.Bottom
  ) { page ->
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      VideoComponent(context, postInfo[page].movieUrl)
//      PostIcons(postInfo[page])
    }
  }
}

@Composable
fun VideoComponent(context: Context, uri: String) {
  val tiktokPlayer = remember {
    SimpleExoPlayer.Builder(context)
      .build()
      .apply {
        val mediaSource = ProgressiveMediaSource.Factory(
          DefaultDataSourceFactory(context, "exoplayer-sample-app")
        )
          .createMediaSource(MediaItem.fromUri(Uri.parse("asset:///${uri}")))
        playWhenReady = true
        setMediaSource(mediaSource)
        prepare()
      }
  }

  AndroidView(
    {
      PlayerView(it).apply {
        useController = false
        player = tiktokPlayer
        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
      }
    },
    modifier = Modifier
      .fillMaxHeight(1f)
  )
}

@Composable
fun PostIcons(
  post: TikTokPost
) {
  Column(
    modifier = Modifier
      .background(Color.Red),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top
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
        .padding(horizontal = 16.dp)
        .width(32.dp)
    )
    Icon(
      painter = painterResource(R.drawable.ic_favorite_border_black_26dp),
      contentDescription = null,
    )
    Text(
      text = post.like.toString()
    )
    Icon(
      painter = painterResource(R.drawable.ic_chat_bubble_outline_black_26dp),
      contentDescription = null,
    )
    Text(
      text = post.comment.toString()
    )
    Icon(
      painter = painterResource(R.drawable.ic_near_me_black_26dp),
      contentDescription = null,
    )
    Text(
      text = post.share.toString()
    )
  }
}
