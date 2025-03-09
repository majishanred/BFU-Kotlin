package com.maji.bfu_kotlin_labs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class RegistrationActivity : AppCompatActivity() {
    private lateinit var storage: SharedPreferences

    private lateinit var usePhoneNumberTV: TextView
    private lateinit var useEmailTV: TextView

    private lateinit var loginInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var repeatPasswordInput: EditText

    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        storage = getSharedPreferences("settings", Context.MODE_PRIVATE)

        usePhoneNumberTV = findViewById(R.id.use_phone_number_tv)
        useEmailTV = findViewById(R.id.use_email_tv)

        loginInput = findViewById(R.id.register_login_input)
        passwordInput = findViewById(R.id.register_password_input)
        repeatPasswordInput = findViewById(R.id.repeat_password_input)

        registerButton = findViewById(R.id.register_button)

        this.initializeListeners()
    }

    private fun initializeListeners() {
        usePhoneNumberTV.setOnClickListener {
            this.changeLoginInputType("phone_number", useEmailTV, usePhoneNumberTV)
        }

        useEmailTV.setOnClickListener {
            this.changeLoginInputType("email", usePhoneNumberTV, useEmailTV)
        }

        registerButton.setOnClickListener {
            val toast = Toast.makeText(this, "", Toast.LENGTH_LONG)

            val message = this.validate()

            if (message.isNotEmpty()) {
                toast.setText(message)
                toast.show()

                return@setOnClickListener
            }

            this.updateDataInStorage()
            this.goNextPage()
        }
    }

    private fun validate(): String {
        var message = ""
        if (loginInput.inputType == InputType.TYPE_CLASS_TEXT && !loginInput.text.toString()
                .contains("@")
        ) {
            message = "Введите корректную электронную почту."
        } else if (loginInput.inputType == InputType.TYPE_CLASS_PHONE && !loginInput.text.toString()
                .contains("+")
        ) {
            message = "Введите корректный номер телефона."
        } else if (passwordInput.text.length < 8) {
            message = "Минимальная длина пароля 8 символов."
        } else if (passwordInput.text.equals(repeatPasswordInput.text)) {
            message = "Введенные пароли разные."
        }

        return message
    }

    private fun updateDataInStorage() {
        storage.edit().putString("login", loginInput.text.toString()).apply()
        storage.edit().putString("password", passwordInput.text.toString()).apply()
    }

    private fun goNextPage() {
        val contentIntent = Intent(this, ContentActivity::class.java)
        startActivity(contentIntent)
    }

    private fun changeLoginInputType(type: String, old_tv: TextView, new_tv: TextView) {
        if (type === "phone_number") {
            loginInput.hint = getString(R.string.enter_phone_number)
            loginInput.inputType = InputType.TYPE_CLASS_PHONE
        } else {
            loginInput.hint = getString(R.string.enter_email)
            loginInput.inputType = InputType.TYPE_CLASS_TEXT
        }

        new_tv.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
        old_tv.setTextColor(ContextCompat.getColor(this, R.color.black))
    }
}