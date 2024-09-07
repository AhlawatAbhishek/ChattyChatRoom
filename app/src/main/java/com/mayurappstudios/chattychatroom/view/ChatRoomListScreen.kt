package com.mayurappstudios.chattychatroom.view

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mayurappstudios.chattychatroom.model.Room

@Composable
fun ChatRoomListScreen(modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text("Chat Rooms", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        //Here we will display the list of chat rooms
        LazyColumn() {

        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                showDialog = true
            }
        ) {
            Text("Create Chat Room")
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = true
            },
            title = {
                Text("Create a new chat room")
            },
            text = {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    singleLine = true,
                    label = { Text("Chat Room Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (name.isNotBlank()) {
                                //Create chat room
                                showDialog = false
                            }
                        }
                    ) {
                        Text("Add")
                    }
                    Button(
                        onClick = {
                            showDialog = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            }
        )
    }
}

@Composable
fun RootItem(room: Room) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = room.name, fontSize = 16.sp, fontWeight = FontWeight.Normal)
        OutlinedButton(onClick = { /*TODO*/
        }) {
            Text("Join")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RootItemPreview() {
    RootItem(Room("1", "Room 1"))
}

@Preview(showBackground = true)
@Composable
fun ChatRoomListScreenPreview() {
    ChatRoomListScreen()
}