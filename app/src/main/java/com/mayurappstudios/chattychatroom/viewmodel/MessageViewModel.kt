package com.mayurappstudios.chattychatroom.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.mayurappstudios.chattychatroom.dependencyinjection.FirestoreProvider
import com.mayurappstudios.chattychatroom.model.Message
import com.mayurappstudios.chattychatroom.repository.MessageRepository
import com.mayurappstudios.chattychatroom.model.Result.Success
import com.mayurappstudios.chattychatroom.model.Result.Error
import com.mayurappstudios.chattychatroom.model.User
import com.mayurappstudios.chattychatroom.repository.UserRepository
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {
    private val _messageRepository: MessageRepository
    private val _userRepository: UserRepository

    init {
        _messageRepository = MessageRepository(FirestoreProvider.instance())
        _userRepository = UserRepository(FirebaseAuth.getInstance(), FirestoreProvider.instance())
        Log.d("MessageViewModel", "Loading current user")
        loadCurrentUser()
    }

    private val _messages = MutableLiveData<List<Message>>()
    val messages: MutableLiveData<List<Message>> = _messages

    private val _roomId = MutableLiveData<String>()
    private val _currentUser = MutableLiveData<User>()
    val currentUser: MutableLiveData<User> get() = _currentUser

    private fun loadCurrentUser() {
        viewModelScope.launch {
            Log.d("MessageViewModel", "Inside Coroutine for loading current user")
            when (val result = _userRepository.getCurrentUser()) {
                is Success -> _currentUser.value = result.data
                is Error -> {
                    // Handle error
                    Log.d("MessageViewModel", "Error loading current user")
                }
            }
        }
    }

    fun loadMessages() {
        viewModelScope.launch {
            if (_roomId.value != null) {
                _messageRepository.getChatMessages(_roomId.value.toString()).collect {
                    _messages.value = it
                    Log.d("MessageViewModel", "Messages size: ${it.size}")
                }
            }
        }
    }

    fun sendMessage(text: String) {
        Log.d("MessageViewModel User: ", "Current user: ${_currentUser.value}")
        if (_currentUser.value != null) {
            Log.d("MessageViewModel", "Sending message: $text")
            val message = Message(
                senderFirstName = _currentUser.value!!.firstName,
                senderId = _currentUser.value!!.email,
                text = text
            )
            viewModelScope.launch() {
                when (_messageRepository.sendMessage(_roomId.value.toString(), message)) {
                    is Success -> {
                        // Handle success
                        Unit;
                    }

                    is Error -> {
                        // Handle error
                    }
                }
            }
        }
    }

    fun setRoomId(roomId: String) {
        _roomId.value = roomId
        loadMessages()
    }
}