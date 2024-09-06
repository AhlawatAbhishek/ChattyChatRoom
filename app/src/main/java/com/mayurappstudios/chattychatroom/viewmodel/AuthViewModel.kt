package com.mayurappstudios.chattychatroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.mayurappstudios.chattychatroom.dependencyinjection.FirestoreProvider
import com.mayurappstudios.chattychatroom.model.UserRepository

class AuthViewModel : ViewModel() {
     private val _userRepository : UserRepository
     init {
          _userRepository = UserRepository(
               _firestore = FirestoreProvider.instance(),
               _auth = FirebaseAuth.getInstance()

          )
     }

     private val _authResult = MutableLiveData<Result<Boolean>>()
     val authResult : MutableLiveData<Result<Boolean>> = _authResult



}