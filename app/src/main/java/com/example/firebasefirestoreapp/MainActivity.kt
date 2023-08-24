package com.example.firebasefirestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.firebasefirestoreapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.fetchUsersDataFromFireStore()
        viewModel.fetchNotesDataFromFireStore()

        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            usersData.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }

            notesData.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }

            error.observe(this@MainActivity) {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}