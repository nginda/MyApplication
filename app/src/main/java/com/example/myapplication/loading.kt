package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class loading : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading)
        Handler().postDelayed({
            val intent: Intent
            intent = Intent(this@loading, Login::class.java)
            startActivity(intent)
            finish() // Finish the loading activity
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME: Long = 5000
    }
}
