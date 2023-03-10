package com.depa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton: Button =findViewById(R.id.signInButton)
        val logInRedirectButton: TextView =findViewById(R.id.logInRedirectButton)

        signInButton.setOnClickListener(){
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        logInRedirectButton.setOnClickListener(){
            val intent= Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}