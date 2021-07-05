package com.example.androidcomposesample.instagram.data

import com.example.androidcomposesample.R

data class InstagramPost(
  val id: Long,
  val user: InstagramUser,
  val postImage: List<Int>,
  val content: String,
  val like: Int
)

val postImageList = listOf(
  R.drawable.post_1_thumb,
  R.drawable.post_2_thumb,
  R.drawable.post_3_thumb,
  R.drawable.post_4_thumb,
  R.drawable.post_5_thumb,
)

val post1 = InstagramPost(
  id = 1L,
  user = user1,
  postImage = postImageList,
  content = "テスト",
  like = 3
)

val postList = listOf(
  post1, post1, post1, post1, post1, post1, post1, post1, post1, post1, post1,
)
