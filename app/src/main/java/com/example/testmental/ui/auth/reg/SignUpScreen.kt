package com.example.testmental.ui.auth.reg

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.testmental.ui.auth.UserViewModel
import com.example.testmental.ui.theme.ColorGradient3

@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: UserViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val state by viewModel.state.collectAsState()

    // 🔽 Эффект при успешной регистрации
    LaunchedEffect(state) {
        if (state is UserViewModel.AuthState.Success) {
            navController.navigate("startSurvey") {
                popUpTo("signUp") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFFEFEFFF), RoundedCornerShape(20.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "👏", fontSize = 28.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Sign Up", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum.",
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 14.sp, color = Color.Gray),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF1F1F1),
                unfocusedContainerColor = Color(0xFFF1F1F1),
                disabledContainerColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email/Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF1F1F1),
                unfocusedContainerColor = Color(0xFFF1F1F1),
                disabledContainerColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF1F1F1),
                unfocusedContainerColor = Color(0xFFF1F1F1),
                disabledContainerColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (isPasswordVisible)
                    Icons.Default.Visibility
                else Icons.Default.VisibilityOff
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.registerUser(
                    name = name,
                    email = email,
                    password = password,
                    onSuccess = {
                        navController.navigate("signIn")
//                        {
//                            popUpTo("signUp") { inclusive = true }
//                        }
                    },
                    onError = { error ->
                        // ничего не делаем здесь, т.к. UI уже обрабатывает ошибки через state
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3366FF))
        ) {
            Text("Create Account", color = Color.White, fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text("Do you have account? ", color = Color.Gray)
            Text(
                "Sign In",
                color = Color(0xFF3366FF),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable {
                    navController.navigate("signIn")
                }
            )
        }

        when (state) {
            is UserViewModel.AuthState.Loading -> CircularProgressIndicator()
            is UserViewModel.AuthState.Success -> {
                Text("User registered successfully!", color = ColorGradient3)
                // Переход в LaunchedEffect выше
            }

            is UserViewModel.AuthState.Error -> {
                val error = (state as UserViewModel.AuthState.Error).message
                Text("Error: $error", color = MaterialTheme.colorScheme.error)
            }

            else -> {}
        }
    }
}
