package com.alkl1m.bfu_kotlin_labs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var storage: SharedPreferences

    private lateinit var loginInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var rememberMeInput: CheckBox

    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        storage = getSharedPreferences("settings", Context.MODE_PRIVATE)

        loginInput = findViewById<EditText>(R.id.login_login_input)
        passwordInput = findViewById<EditText>(R.id.login_password_input)

        loginButton = findViewById<Button>(R.id.login_button)

        rememberMeInput = findViewById<CheckBox>(R.id.remember_me_cb)

        this.initializeListeners()
    }

    private fun initializeListeners() {
        loginButton.setOnClickListener {
            this.login()
        }
    }

    private fun login() {
        val login = storage.getString("login", null)
        val password = storage.getString("password", null)

        if(loginInput.text.toString().equals(login) && passwordInput.text.toString().equals(password)) {
            this.updateDataInStorage()

            this.goNextPage()
        } else {
            val toast = Toast.makeText(this, "Не найден аккаунт с введенными данными.", Toast.LENGTH_LONG)
            toast.show()
        }
    }

    private fun updateDataInStorage() {
        storage.edit().putBoolean("remember_me", rememberMeInput.isChecked).apply()
    }

    private fun goNextPage() {
        val contentIntent = Intent(this, ContentActivity::class.java)
        startActivity(contentIntent)
    }
}