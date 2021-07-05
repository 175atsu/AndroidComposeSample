package com.example.androidcomposesample.tiktok.data

data class TikTokUser(
  val id: Long,
  val name: String,
  val image: String
)

val user1 = TikTokUser(
  1L,
  "messi",
  "https://pbs.twimg.com/profile_images/1236196827168305152/3QIg9fpd_400x400.jpg"
)
