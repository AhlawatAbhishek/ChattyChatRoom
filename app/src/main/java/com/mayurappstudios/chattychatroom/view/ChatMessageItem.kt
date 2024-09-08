package com.mayurappstudios.chattychatroom.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mayurappstudios.chattychatroom.R
import com.mayurappstudios.chattychatroom.model.Message
import com.mayurappstudios.chattychatroom.utils.formatTimeStamp
@Composable
@RequiresApi(Build.VERSION_CODES.P)
fun ChatMessageItem(modifier: Modifier = Modifier, message: Message) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = if (message.isSentByCurrentUser) Alignment.End else Alignment.Start
    ) {
        //Here we will display the chat message
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isSentByCurrentUser) colorResource(id = R.color.purple_700) else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        ) {
            //Display the message
            Text(text = message.text, color = Color.White, style = TextStyle(fontSize = 16.sp))
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = message.senderFirstName,
            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
        )
        Text(
            text = formatTimeStamp(message.timeStamp),
            style = TextStyle(fontSize = 12.sp, color = Color.Gray)
        )
    }
}