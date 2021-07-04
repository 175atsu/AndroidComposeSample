package com.example.androidcomposesample.instagram.data

data class InstagramPost(
  val id: Long,
  val user: InstagramUser,
  val postImage: String,
  val content: String,
  val like: Int
)

val post1 = InstagramPost(
  id = 1L,
  user = user1,
  postImage = "https://pbs.twimg.com/profile_images/1236196827168305152/3QIg9fpd_400x400.jpg",
  content = "テスト",
  like = 3
)

val postList = listOf(
  post1,
  post1, post1, post1, post1, post1, post1, post1, post1, post1, post1,
)
