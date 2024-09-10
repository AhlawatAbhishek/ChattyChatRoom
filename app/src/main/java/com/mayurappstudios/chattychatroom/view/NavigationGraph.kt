package com.mayurappstudios.chattychatroom.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mayurappstudios.chattychatroom.viewmodel.AuthViewModel


@RequiresApi(Build.VERSION_CODES.P)
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
                    navController.navigate(Screen.ChatRoom.route)
                }) {
                navController.navigate(Screen.SignUp.route)
            }
        }
        composable(Screen.SignUp.route) {
            SignUpScreen(modifier = modifier, authViewModel = authViewModel) {
                navController.navigate(Screen.Login.route)
            }
        }
        composable(Screen.ChatRoom.route) {
            ChatRoomListScreen(modifier = modifier){
                navController.navigate("${Screen.Chat.route}/${it.id}}")
            }
        }
        composable("${Screen.Chat.route}/{roomId}") { backStackEntry ->
            val roomId = backStackEntry.arguments?.getString("roomId") ?: ""
            ChatScreen(modifier = modifier, roomId = roomId)
        }
    }

}