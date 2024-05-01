package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Signup : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var departmentEditText: EditText
    private lateinit var maleRadioButton: RadioButton
    private lateinit var femaleRadioButton: RadioButton
    private lateinit var signUpButton: Button
    //private lateinit var loginTextView
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        dbHelper = DatabaseHelper(this)

        // Initialize views
        firstNameEditText = findViewById(R.id.editTextFirstName)
        lastNameEditText = findViewById(R.id.editTextLastName)
        departmentEditText = findViewById(R.id.editTextDepartment)
        maleRadioButton = findViewById(R.id.radioButton)
        femaleRadioButton = findViewById(R.id.radioButton2)
        signUpButton = findViewById(R.id.buttonSignUp)
        //loginTextView = findViewById(R.id.textViewLogin)

        // Set click listener for sign up button
        signUpButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val department = departmentEditText.text.toString()
            val gender = if (maleRadioButton.isChecked) "Male" else "Female"

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && department.isNotEmpty()) {
                val password = "some_default_password" // For simplicity
                dbHelper.addUser(firstName, lastName, password)
                Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show()
                navigateToLogin()
            } else {
                Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun navigateToLogin() {
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
        finish()
    }
}
