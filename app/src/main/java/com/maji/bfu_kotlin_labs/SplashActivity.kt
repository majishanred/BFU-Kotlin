package com.alkl1m.bfu_kotlin_labs

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        this.checkStorageData()
    }

    private fun checkStorageData() {
        val storage = getSharedPreferences("settings", Context.MODE_PRIVATE)

        val login = storage.getString("login", null)
        val password = storage.getString("password", null)
        val rememberMe = storage.getBoolean("remember_me", false)

        if (rememberMe && login !== null && password !== null) {
            val contentIntent = Intent(this, ContentActivity::class.java)
            startActivity(contentIntent)
        } else if (!rememberMe && login !== null && password !== null) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
        } else {
            val registrationIntent = Intent(this, RegistrationActivity::class.java)
            startActivity(registrationIntent)
        }
    }
}