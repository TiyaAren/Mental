package com.example.testmental.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.domain.model.User
import com.example.testmental.domain.usecase.LoginUserUseCase
import com.example.testmental.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AuthState>(AuthState.Idle)
    val state: StateFlow<AuthState> = _state

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val user = loginUserUseCase(email, password)
                if (user != null) {
                    _state.value = AuthState.Success("Login successful")
                    onSuccess()
                } else {
                    _state.value = AuthState.Error("Invalid credentials")
                    onError("Invalid credentials")
                }
            } catch (e: Exception) {
                val message = e.message ?: "Login failed"
                _state.value = AuthState.Error(message)
                onError(message)
            }
        }
    }

    fun registerUser(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            try {
                val user = User(name = name, email = email, password = password)
                registerUserUseCase(user)
                _state.value = AuthState.Success("Registration successful")
                onSuccess()
            } catch (e: Exception) {
                val message = e.message ?: "Registration failed"
                _state.value = AuthState.Error(message)
                onError(message)
            }
        }
    }

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val message: String) : AuthState()
        data class Error(val message: String) : AuthState()
    }
}
