package com.example.firebasefirestoreapp.models

data class UsersResponse(
    val email: String,
    val name: String,
){
    constructor() : this("", "")
}