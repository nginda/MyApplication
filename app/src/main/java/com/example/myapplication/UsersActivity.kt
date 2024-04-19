package com.example.myapplication

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class UsersActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var dbHelper: DatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        listView = findViewById(R.id.listViewUsers)
        dbHelper = DatabaseHelper(this)

        displayUsers()
    }

    private fun displayUsers() {
        val userList = dbHelper.getAllUsers()
        val adapter = UserAdapter(this, userList)
        listView.adapter = adapter
    }



}
