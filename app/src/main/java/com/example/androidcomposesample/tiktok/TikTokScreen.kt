package com.example.androidcomposesample.tiktok

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
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
  val videos = listOf("t1.mp4", "t2.mp4", "t3.mp4")

  VerticalPager(state = pagerState) { page ->
    VideoComponent(context, videos[page])
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
