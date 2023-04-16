package com.example.rinjin.domain.useCase

import com.example.rinjin.common.NetworkResponse
import com.example.rinjin.data.remote.toUser
import com.example.rinjin.domain.model.User
import com.example.rinjin.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UsersUseCase @Inject constructor(
    private val repository: UsersRepository,
) {
    operator fun invoke(location: String): Flow<NetworkResponse<List<User>>> = flow {
        try {
            emit(NetworkResponse.Loading<List<User>>())
            val users = repository.getUsers(location).toUser()
            emit(NetworkResponse.Success<List<User>>(users))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure(error = e.message.toString()))
        }
    }
}
