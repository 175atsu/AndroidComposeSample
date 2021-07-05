package com.example.androidcomposesample.tiktok.data

data class TikTokPost(
  val id: Long,
  val user: TikTokUser,
  val like: Int,
  val comment: Int,
  val share: Int,
  val content: String,
  val movieUrl: String,
  val musicTitle: String
)

val post1 = TikTokPost(
  1L,
  user1,
  1200,
  23,
  234,
  "テストテスト",
  "t1.mp4",
  "アーティストアーティストアーティストアーティストアーティストアーティスト"
)

val post2 = TikTokPost(
  2L,
  user1,
  2400,
  3,
  345,
  "テストテスト2",
  "t2.mp4",
  "アーティストアーティストアーティストアーティストアーティストアーティスト"
)

val post3 = TikTokPost(
  3L,
  user1,
  1234455,
  2311,
  23411,
  "テストテストテストテストテストテストテストテストテストテストテストテストテストテスト",
  "t3.mp4",
  "アーティストアーティストアーティストアーティストアーティストアーティスト"
)

val postList = listOf(
  post1, post2, post3
)
