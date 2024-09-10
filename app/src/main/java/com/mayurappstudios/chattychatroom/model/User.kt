package com.mayurappstudios.chattychatroom.model

data class User(
    val firstName : String,
    val lastName : String,
    val email : String
){
    // Empty constructor for Firebase
    constructor() : this("","","")
}
