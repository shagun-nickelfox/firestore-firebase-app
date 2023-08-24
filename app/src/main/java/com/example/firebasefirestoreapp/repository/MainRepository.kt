package com.example.firebasefirestoreapp.repository

import com.example.firebasefirestoreapp.Result
import com.example.firebasefirestoreapp.fetchFireStoreData
import com.example.firebasefirestoreapp.models.NoteResponse
import com.example.firebasefirestoreapp.models.UsersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {

    suspend fun fetchUsersData(collectionName: String): Result<List<UsersResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = fetchFireStoreData<UsersResponse>(collectionName)
                response
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }

    suspend fun fetchNotesData(collectionName: String): Result<List<NoteResponse>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = fetchFireStoreData<NoteResponse>(collectionName)
                response
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
    }
}