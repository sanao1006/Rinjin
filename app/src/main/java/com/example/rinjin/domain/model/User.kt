package com.example.rinjin.domain.model

data class User(
    val name: String,
    val url: String?,
    val avatar_url: String?,
    val follower_url: Int,
)
