package com.example.rinjin.data.remote

import com.example.rinjin.common.Constants
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiClient {
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") location: String,
        @Query("sort") sort: String = "followers",
        @Query("order") order: String = "desc",
    ): SearchUsersResultDto

    @GET("users/{username}")
    suspend fun getFollower(
        @Path("username") username: String,
        @Header("Authorization") token: String = "Bearer ${Constants.API_TOKEN}",
    ): SearchUserDto
}
