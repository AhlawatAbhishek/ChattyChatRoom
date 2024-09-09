package com.mayurappstudios.chattychatroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mayurappstudios.chattychatroom.dependencyinjection.FirestoreProvider
import com.mayurappstudios.chattychatroom.model.Message
import com.mayurappstudios.chattychatroom.model.MessageRepository
import com.mayurappstudios.chattychatroom.model.Result.Success
import com.mayurappstudios.chattychatroom.model.Result.Error
import com.mayurappstudios.chattychatroom.model.User
import com.mayurappstudios.chattychatroom.model.UserRepository
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {
    private val _messageRepository : MessageRepository
    private val _userRepository : UserRepository
    init{
        _messageRepository = MessageRepository(FirestoreProvider.instance())
        _userRepository = UserRepository(FirebaseAuth.getInstance(), FirestoreProvider.instance())
        loadCurrentUser()
    }
    private val _messages = MutableLiveData<List<Message>>()
    val messages : MutableLiveData<List<Message>> = _messages

    private val _roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val currentUser : MutableLiveData<User> get() = _currentUser

    private fun loadCurrentUser{
        viewModelScope.launch {
            when(val result = _userRepository.getCurrentUser()){
                is Success<*> -> _currentUser.value = result.data
                is Error -> {
                    // Handle error
                }
            }
        }
    }
    fun loadMessages(){
        viewModelScope.launch {
            if(_roomId.value != null){
                if(_roomId != null){

                }
            }
        }
    }
}