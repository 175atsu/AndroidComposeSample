package com.example.androidcomposesample.instagram

import java.util.UUID

data class User(
  val id: Long,
  val name: String,
  val image: String
)

private val user1 = User(
  id = 1L,
  name = "messi",
  image = "https://pbs.twimg.com/profile_images/1236196827168305152/3QIg9fpd_400x400.jpg"
)

val userList = listOf(
  user1,
  user1,
  user1, user1, user1, user1, user1, user1, user1, user1, user1, user1,
)

fun dummyUser(): List<User> {
  return userList
}
