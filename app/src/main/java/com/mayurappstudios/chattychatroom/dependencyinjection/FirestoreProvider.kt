package com.mayurappstudios.chattychatroom.dependencyinjection

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreProvider {
    private val instance : FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }
    fun instance() : FirebaseFirestore = instance
}