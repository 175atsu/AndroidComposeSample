package com.example.androidcomposesample.instagram

import androidx.compose.foundation.Image
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.transform.CircleCropTransformation
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun InstagramActionIcon(
  imageResource: Int,
  modifier: Modifier = Modifier
) {
  Icon(
    painter = painterResource(imageResource),
    contentDescription = null,
    modifier = modifier
  )
}

@Composable
fun CircleImage(
  imageUrl: String,
  modifier: Modifier
) {
  Image(
    painter = rememberCoilPainter(
      request = imageUrl,
      requestBuilder = {
        transformations(CircleCropTransformation())
      }
    ),
    contentDescription = null,
    modifier = modifier
  )
}
