package com.example.rinjin.data.repository

import com.example.rinjin.data.remote.GithubApiClient
import com.example.rinjin.data.remote.SearchUsersResultDto
import com.example.rinjin.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val api: GithubApiClient) : UsersRepository {
    override suspend fun getUsers(location: String): SearchUsersResultDto {
        return api.getUsers(location)
    }
}
