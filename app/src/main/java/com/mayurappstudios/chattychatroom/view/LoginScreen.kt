package com.mayurappstudios.chattychatroom.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import com.mayurappstudios.chattychatroom.model.Result
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayurappstudios.chattychatroom.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    authViewModel: AuthViewModel? = null,
    onSignInSuccess: () -> Unit = {},
    onNaviagteToSignUp: () -> Unit = {}
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authResult  =  authViewModel?.authResult?.observeAsState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Button(
            onClick = {
                authViewModel?.login(email, password)
                when(authResult?.value) {
                    is Result.Success -> {
                        onSignInSuccess()
                    }
                    is  Result.Error -> {
                        // Handle error
                    }
                    else -> {
                        // Handle loading
                    }
                }
            },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Don't have an account? Sign up.",
            modifier = Modifier
                .padding(8.dp)
                .clickable {
                    onNaviagteToSignUp()
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}