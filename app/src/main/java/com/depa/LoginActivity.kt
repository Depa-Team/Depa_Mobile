package com.depa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val logInButton: Button=findViewById(R.id.logInButton)
        val signInRedirectButton: TextView=findViewById(R.id.signInRedirectButton)

        logInButton.setOnClickListener(){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        signInRedirectButton.setOnClickListener(){
            val intent= Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}