package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var createAccountTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        // Initialize views
        firstNameEditText = findViewById(R.id.editTextFirstName)
        lastNameEditText = findViewById(R.id.editTextLastName)
        passwordEditText = findViewById(R.id.passwordField)
        loginButton = findViewById(R.id.loginButton)
        createAccountTextView = findViewById(R.id.textViewLogin)

        // Set click listener for login button
        loginButton.setOnClickListener {
            // Directly move to the next activity without any database interaction
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Set click listener for "Create account" text view
        createAccountTextView.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }
    }
}
