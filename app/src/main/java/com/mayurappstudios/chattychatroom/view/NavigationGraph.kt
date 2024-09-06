package com.mayurappstudios.chattychatroom.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavigationGraph(modifier: Modifier = Modifier, navController : NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SignUp.route) {
        composable(Screen.Login.route) {
            LoginScreen(modifier = modifier) {
                navController.navigate(Screen.SignUp.route)
            }
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(modifier = modifier) {
                navController.navigate(Screen.Login.route)
            }
        }
    }

}