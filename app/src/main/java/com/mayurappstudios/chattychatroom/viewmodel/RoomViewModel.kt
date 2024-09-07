package com.mayurappstudios.chattychatroom.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurappstudios.chattychatroom.model.Result.Success
import com.mayurappstudios.chattychatroom.model.Result.Error
import com.mayurappstudios.chattychatroom.dependencyinjection.FirestoreProvider
import com.mayurappstudios.chattychatroom.model.Room
import com.mayurappstudios.chattychatroom.model.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
    private val _rooms =  MutableLiveData<List<Room>>()
    val rooms : MutableLiveData<List<Room>> = _rooms
    private val roomRepository : RoomRepository
    init {
        roomRepository = RoomRepository(FirestoreProvider.instance())
        loadRooms()
    }
    fun createRoom(name : String){
        viewModelScope.launch{
            roomRepository.createRoom(name)
        }
    }
    fun loadRooms(){
        viewModelScope.launch {
            when(val result = roomRepository.getRooms()){
                is Success -> _rooms.value = result.data
                is Error -> {
                    // Handle error
                }
            }
        }
    }
}