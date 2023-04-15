package com.example.rinjin.domain.repository

import com.example.rinjin.data.remote.SearchUsersResultDto

interface UsersRepository {
    suspend fun getUsers(location: String): SearchUsersResultDto
}
