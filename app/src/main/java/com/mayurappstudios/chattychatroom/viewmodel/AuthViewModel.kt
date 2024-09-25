package com.mayurappstudios.chattychatroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mayurappstudios.chattychatroom.dependencyinjection.FirestoreProvider
import com.mayurappstudios.chattychatroom.repository.UserRepository
import kotlinx.coroutines.launch
import com.mayurappstudios.chattychatroom.model.Result

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

    fun singUp(email : String, password : String, firstName : String, lastName : String) {
        viewModelScope.launch {
            _authResult.value = _userRepository.signUp(email, password, firstName, lastName)
        }
    }
    fun login(email : String, password : String) {
        viewModelScope.launch {
            _authResult.value = _userRepository.login(email, password)
        }
    }

}