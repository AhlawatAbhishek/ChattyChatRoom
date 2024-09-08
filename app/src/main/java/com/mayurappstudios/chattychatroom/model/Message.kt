package com.mayurappstudios.chattychatroom.model

data class Message(
    val senderFirstName : String = "",
    val senderId : String = "",
    val text : String = "",
    val timeStamp : Long = System.currentTimeMillis(),
    val isSentByCurrentUser : Boolean = false
)
