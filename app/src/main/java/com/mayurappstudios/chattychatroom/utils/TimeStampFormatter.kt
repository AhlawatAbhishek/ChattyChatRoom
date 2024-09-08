package com.mayurappstudios.chattychatroom.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant.ofEpochSecond
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
private fun formatTimeStamp(timeStamp : Long) : String{
    val messageDateTime = LocalDateTime.ofInstant(ofEpochSecond(timeStamp), ZoneId.systemDefault())
    val currentDateTime = LocalDateTime.now()
    return when{
          isSameDay(messageDateTime, currentDateTime) -> formatTime(messageDateTime)
          isSameDay(messageDateTime.plusDays(1), currentDateTime) -> "Yesterday"
          else -> formatDate(messageDateTime)
    }
}
@RequiresApi(Build.VERSION_CODES.O)
private fun isSameDay(messageDateTime: LocalDateTime, currentDateTime: LocalDateTime): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return messageDateTime.format(formatter) == currentDateTime.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun formatTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH : mm")
    return dateTime.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
private fun formatDate(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM, yyyy")
    return dateTime.format(formatter)
}