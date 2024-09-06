package com.mayurappstudios.chattychatroom.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mayurappstudios.chattychatroom.viewmodel.AuthViewModel


@Composable
fun NavigationGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(navController = navController, startDestination = Screen.SignUp.route) {
        composable(Screen.Login.route) {
            LoginScreen(modifier = modifier, authViewModel = authViewModel,
                onSignInSuccess = {
                    navController.navigate(Screen.Chat.route)
                }) {
                navController.navigate(Screen.SignUp.route)
            }
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(modifier = modifier, authViewModel = authViewModel) {
                navController.navigate(Screen.Login.route)
            }
        }
    }

}