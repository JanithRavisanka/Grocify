package com.example.grocify

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        // Sign In button - just go to home page
        findViewById<Button>(R.id.signin_button).setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }


    }
}
