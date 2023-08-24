package com.example.firebasefirestoreapp

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

suspend inline fun <reified T> fetchFireStoreData(collectionName: String): Result<List<T>> {
    val db = FirebaseFirestore.getInstance()

    return try {
        val querySnapshot = db.collection(collectionName).get().await()
        val dataList = mutableListOf<T>()

        for (document in querySnapshot) {
            val data = document.toObject(T::class.java)
            dataList.add(data)
        }

        Result.Success(dataList)
    } catch (exception: Exception) {
        Result.Error(exception)
    }
}












