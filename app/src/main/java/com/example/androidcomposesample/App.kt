package com.example.androidcomposesample

import android.content.Context
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun App(context: Context) {
  MaterialTheme {
    NavGraph(context = context)
  }
}
