package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UserDatabase"
        private const val TABLE_USERS = "users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRST_NAME = "first_name"
        private const val COLUMN_LAST_NAME = "last_name"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_USERS_TABLE = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY,"
                + "$COLUMN_FIRST_NAME TEXT,"
                + "$COLUMN_LAST_NAME TEXT,"
                + "$COLUMN_PASSWORD TEXT)")
        db?.execSQL(CREATE_USERS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(firstName: String, lastName: String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_FIRST_NAME, firstName)
        values.put(COLUMN_LAST_NAME, lastName)
        values.put(COLUMN_PASSWORD, password)
        val id = db.insert(TABLE_USERS, null, values)
        db.close()
        return id
    }

    fun getUser(firstName: String, lastName: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_FIRST_NAME=? AND $COLUMN_LAST_NAME=? AND $COLUMN_PASSWORD=?"
        val cursor: Cursor? = db.rawQuery(query, arrayOf(firstName, lastName, password))
        val count = cursor?.count ?: 0
        cursor?.close()
        return count > 0
    }

    data class User(
        val firstName: String,
        val lastName: String,
        val password: String
    )

    @SuppressLint("Range")
    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS"
        val cursor = db.rawQuery(query, null)
        cursor?.use {
            while (it.moveToNext()) {
                val user = User(
                    it.getString(it.getColumnIndex(COLUMN_FIRST_NAME)),
                    it.getString(it.getColumnIndex(COLUMN_LAST_NAME)),
                    it.getString(it.getColumnIndex(COLUMN_PASSWORD))
                )
                userList.add(user)
            }
        }
        cursor?.close()
        return userList
    }
}
