package com.example.rinjin.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.rinjin.common.NetworkResponse
import com.example.rinjin.domain.useCase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
) : ViewModel() {
    private val _usersState: MutableState<UsersState> = mutableStateOf(UsersState())
    val usersState: State<UsersState> = _usersState

    fun getUsers(location: String) {
        usersUseCase(location).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    _usersState.value = UsersState(
                        isLoading = false,
                        users = result.data ?: emptyList(),
                    )
                }
                is NetworkResponse.Failure -> {
                    _usersState.value = UsersState(
                        error = result.error,
                    )
                }
                is NetworkResponse.Loading -> {
                    _usersState.value = UsersState(
                        isLoading = true,
                    )
                }
            }
        }
    }
}
