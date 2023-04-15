package com.example.rinjin.data.remote

import Item
import com.example.rinjin.domain.model.User
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@JsonClass(generateAdapter = true)
data class SearchUsersResultDto(
    @Json(name = "incomplete_results")
    val incompleteResults: Boolean?,
    val items: List<Item?>?,
    @Json(name = "total_count")
    val totalCount: Int?,
)

val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
    .addConverterFactory(
        MoshiConverterFactory.create(
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )
    ).build().create(GithubApiClient::class.java)
suspend fun SearchUsersResultDto.toUser(): List<User>{
    return items!!.map{
        val followerCount = getFollowerCount(it!!.login!!)
        User(
            name = it!!.login!!,
            url = it!!.htmlUrl ?: "#",
            avatar_url = it!!.avatarUrl ?: "#",
            follower_val = followerCount
        )
    }
}

suspend fun getFollowerCount(username: String): Int{
    val follower = retrofit.getFollower(username = username)
    return follower.followers ?: 0
}
