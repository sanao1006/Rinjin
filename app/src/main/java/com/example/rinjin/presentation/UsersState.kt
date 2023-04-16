package com.example.rinjin.presentation

import com.example.rinjin.domain.model.User

data class UsersState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null,
)
