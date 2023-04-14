package com.example.rinjin.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApiClient {
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") location: String,
        @Query("sort") sort: String = "followers",
        @Query("order") order: String = "desc"
    ): SearchUsersResultDto

}
