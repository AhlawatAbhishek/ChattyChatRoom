package com.mayurappstudios.chattychatroom.model

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

class MessageRepository(private val firestore: FirebaseFirestore) {
    suspend fun sendMessage(roomId: String, message: Message): Result<Unit> = try {
        firestore.collection("rooms").document(roomId).collection("messages").add(message).await()
        Result.Success(Unit)
    } catch (e: Exception) {
        Result.Error(e)
    }
    fun getChatMessages(roomId: String) : Flow<List<Message>> = callbackFlow {
        val subscription =
    }
}