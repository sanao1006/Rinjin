package com.example.rinjin.domain.repository

import com.example.rinjin.data.remote.SearchUserDto
import com.example.rinjin.data.remote.SearchUsersResultDto

interface UsersRepository {
    suspend fun getUsers(location: String): SearchUsersResultDto

    suspend fun getFollower(username: String): SearchUserDto
}
