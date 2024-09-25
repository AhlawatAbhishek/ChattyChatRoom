package com.mayurappstudios.chattychatroom.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mayurappstudios.chattychatroom.model.Result
import com.mayurappstudios.chattychatroom.model.User
import kotlinx.coroutines.tasks.await

class UserRepository(private val _auth: FirebaseAuth, private val _firestore: FirebaseFirestore) {
    suspend fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ): Result<Boolean> =
        try {
            _auth.createUserWithEmailAndPassword(email, password).await()
            val user = User(firstName, lastName, email)
            saveUser(user)
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    private suspend fun saveUser(user: User) {
        _firestore.collection("users").document(user.email).set(user).await()
    }

    suspend fun login(email: String, password: String): Result<Boolean> =
        try {
            _auth.signInWithEmailAndPassword(email, password).await()
            Result.Success(true)
        } catch (e: Exception) {
            Result.Error(e)
        }

    suspend fun getCurrentUser(): Result<User> =
        try {
            val email = _auth.currentUser?.email
            if (email != null)
                Log.d("UserRepository", "Current user email: $email")
            else
                Log.d("UserRepository", "Current user email: null")
            if (email != null) {
                val user = _firestore.collection("users").document(email).get().await()
                    .toObject(User::class.java)
                if (user != null) {
                    Log.d("UserRepository", "User found, userEmail: ${user.email}")
                    Result.Success(user)
                } else {
                    Result.Error(Exception("User not found"))
                }
            } else {
                Result.Error(Exception("User not found"))
            }
        } catch (e: Exception) {
            Log.d("UserRepository", "Error getting current user: ")
            Result.Error(e)
        }
}