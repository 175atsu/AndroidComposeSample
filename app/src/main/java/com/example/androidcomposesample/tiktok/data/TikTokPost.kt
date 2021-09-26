package com.example.androidcomposesample.tiktok.data

data class TikTokPost(
  val id: Long,
  val user: TikTokUser,
  val like: Int,
  val comment: Int,
  val share: Int,
  val content: String,
  val movieUrl: String,
  val musicTitle: String,
  val musicThumbnail: String
)

val post1 = TikTokPost(
  1L,
  user1,
  1200,
  23,
  234,
  "テストテスト",
  "move1.mp4",
  "アーティストアーティストアーティストアーティストアーティストアーティスト",
  "https://i1.sndcdn.com/artworks-tWZztUxXhQ5k12Ro-Nj8SGw-t500x500.jpg"
)

val post2 = TikTokPost(
  2L,
  user1,
  2400,
  3,
  345,
  "テストテスト2テストテスト2テストテスト2テストテスト2テストテスト2テストテスト2テストテスト2",
  "move2.mp4",
  "アーティストアーティストアーティストアーティストアーティストアーティスト",
  "https://i1.sndcdn.com/artworks-tWZztUxXhQ5k12Ro-Nj8SGw-t500x500.jpg"
)

val post3 = TikTokPost(
  3L,
  user1,
  1234455,
  2311,
  23411,
  "テストテストテストテストテストテストテストテストテストテストテストテストテストテスト",
  "move3.mp4",
  "アーティストアーティストアーティストアーティストアーティストアーティスト",
  "https://i1.sndcdn.com/artworks-tWZztUxXhQ5k12Ro-Nj8SGw-t500x500.jpg"
)

val postList = listOf(
  post1, post2, post3
)
