package com.example.androidcomposesample.tiktok

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TikTokIconComponent(
  iconResource: Int,
  number: String
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Icon(
      painter = painterResource(iconResource),
      contentDescription = null,
      modifier = Modifier.size(40.dp),
      tint = Color.White
    )
    Text(
      text = number,
      color = Color.White,
      fontSize = 12.sp
    )
  }
}

@Composable
fun TikTokInfoTextComponent(
  label: String,
  modifier: Modifier = Modifier
) {
  Text(
    text = label,
    color = Color.White,
    fontSize = 14.sp,
    modifier = modifier
  )
}
