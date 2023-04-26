package com.example.rinjin.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rinjin.common.NetworkResponse
import com.example.rinjin.domain.useCase.UsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val usersUseCase: UsersUseCase,
) : ViewModel() {
    private val _usersState: MutableState<UsersState> = mutableStateOf(UsersState())
    val usersState: State<UsersState> = _usersState

    private val _locationInputText: MutableStateFlow<String> = MutableStateFlow("")
    val locationInputText: StateFlow<String> = _locationInputText

    fun changeLocation(location: String) {
        _locationInputText.value = location
    }
    fun clearLocation() {
        _locationInputText.value = ""
    }
    fun getUsers(location: String) {
        usersUseCase(location).onEach { result ->
            when (result) {
                is NetworkResponse.Success -> {
                    Log.d("state:", "success")
                    _usersState.value = UsersState(
                        isLoading = false,
                        users = result.data ?: emptyList(),
                    )
                }
                is NetworkResponse.Failure -> {
                    Log.d("state:", "failure")
                    _usersState.value = UsersState(
                        error = result.error,
                    )
                }
                is NetworkResponse.Loading -> {
                    Log.d("state:", "loading")
                    _usersState.value = UsersState(
                        isLoading = true,
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}
