package com.example.androidcomposesample.instagram.data

data class InstagramUser(
  val id: Long,
  val name: String,
  val image: String
)

val user1 = InstagramUser(
  id = 1L,
  name = "messi",
  image = "https://pbs.twimg.com/profile_images/1236196827168305152/3QIg9fpd_400x400.jpg"
)

val userList = listOf(
  user1,
  user1,
  user1, user1, user1, user1, user1, user1, user1, user1, user1, user1,
)

fun dummyUser(): List<InstagramUser> {
  return userList
}
