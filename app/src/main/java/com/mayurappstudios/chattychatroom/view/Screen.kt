package com.mayurappstudios.chattychatroom.view

sealed class Screen (val route : String){
    object Login : Screen("login_screen")
    object SignUp : Screen("sign_up_screen")
    object Chat : Screen("chat_room_screen")
    object ChatRoom : Screen("chat_screen")
}