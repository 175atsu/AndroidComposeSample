package com.example.androidcomposesample.grid

import android.os.Bundle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun GridDetailPage(args: Bundle?) {
    val label = args?.getString("indexLabel")
    if (label != null) {
        Text(text = label)
    }
}

@Preview("gridDetailPage")
@Composable
fun PreviewGridDetail() {
}