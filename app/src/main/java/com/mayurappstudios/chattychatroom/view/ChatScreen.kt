package com.mayurappstudios.chattychatroom.view

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mayurappstudios.chattychatroom.viewmodel.MessageViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ChatScreen(modifier: Modifier = Modifier,  roomId : String = "", messageViewModel: MessageViewModel = viewModel()) {
    var text by remember { mutableStateOf("") }
    val messages by messageViewModel.messages.observeAsState(emptyList())
    messageViewModel.setRoomId(roomId)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        //Display the chat messages here
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages) { message ->
                //Here we will display the message
                ChatMessageItem(message = message.copy(isSentByCurrentUser = message.senderId == messageViewModel.currentUser.value?.email))
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Here we will add the text field and send button
            BasicTextField(
                value = text,
                onValueChange = { text = it },
                textStyle = TextStyle.Default.copy(fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
            IconButton(
                onClick = {
                    if (text.isNotEmpty()) {
                        //Send the message
                        messageViewModel.sendMessage(text.trim())
                        Log.d("ChatScreen", "Message sent: $text")
                        text = ""
                    }
                    messageViewModel.loadMessages()
                }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send"
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreen()
}