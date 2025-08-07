package com.example.testmental.ui.auth.reg


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmental.domain.model.User
import com.example.testmental.domain.usecase.RegisterUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> = _state

    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading

            try {
                val user = User(name = name, email = email, password = password)
                registerUserUseCase(user)
                _state.value = RegisterState.Success
            } catch (e: Exception) {
                _state.value = RegisterState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
