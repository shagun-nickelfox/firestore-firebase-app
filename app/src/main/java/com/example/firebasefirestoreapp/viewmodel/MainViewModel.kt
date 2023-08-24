package com.example.firebasefirestoreapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasefirestoreapp.Result
import com.example.firebasefirestoreapp.models.NoteResponse
import com.example.firebasefirestoreapp.models.UsersResponse
import com.example.firebasefirestoreapp.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()

    private val _usersData = MutableLiveData<List<UsersResponse>>()
    val usersData: LiveData<List<UsersResponse>> = _usersData
    private val _notesData = MutableLiveData<List<NoteResponse>>()
    val notesData: LiveData<List<NoteResponse>> = _notesData
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchUsersDataFromFireStore() {
        viewModelScope.launch {
            when (val response =
                mainRepository.fetchUsersData("users")) {
                is Result.Success -> {
                    val dataList = response.data
                    _usersData.postValue(dataList)
                }

                is Result.Error -> {
                    _error.postValue(response.exception.localizedMessage)
                }
            }
        }
    }

    fun fetchNotesDataFromFireStore() {
        viewModelScope.launch {
            when (val response =
                mainRepository.fetchNotesData("notes")) {
                is Result.Success -> {
                    val dataList = response.data
                    _notesData.postValue(dataList)
                }

                is Result.Error -> {
                    _error.postValue(response.exception.localizedMessage)
                }
            }
        }
    }
}