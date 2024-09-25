package com.mayurappstudios.chattychatroom.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.mayurappstudios.chattychatroom.model.Message
import com.mayurappstudios.chattychatroom.model.Result
import kotlinx.coroutines.channels.awaitClose
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
    fun getChatMessages(roomId: String): Flow<List<Message>> = callbackFlow {
        Log.d("MessageRepository", "Room ID: $roomId")
        val subscription = firestore.collection("rooms").document(roomId)
            .collection("messages")
            .orderBy("timeStamp")
            .addSnapshotListener { querySnapshot, error ->
                if (error != null) {
                    Log.e("MessageRepository", "Listen failed.", error)
                    return@addSnapshotListener
                }
                if (querySnapshot != null) {
                    Log.d("MessageRepository", "QuerySnapshot size: ${querySnapshot.size()}")
                    val messages = querySnapshot.documents.mapNotNull { document ->
                        document.toObject(Message::class.java)
                    }
                    Log.d("MessageRepository", "Messages size: ${messages.size}")
                    trySend(messages).isSuccess
                } else {
                    Log.d("MessageRepository", "QuerySnapshot is null")
                }
            }

        awaitClose { subscription.remove() }
    }

//    fun getChatMessages(roomId: String): Flow<List<Message>> = callbackFlow {
//        Log.d("MessageRepository", "Room ID: $roomId")
//        val subscription = firestore.collection("rooms").document(roomId)
//            .collection("messages")
//            .orderBy("timestamp")
//            .addSnapshotListener { querySnapshot, _ ->
//                querySnapshot?.let {
//                    trySend(it.documents.map { doc ->
//                        doc.toObject(Message::class.java)!!.copy()
//                    }).isSuccess
//                }
//            }
//
//        awaitClose { subscription.remove() }
//    }
}