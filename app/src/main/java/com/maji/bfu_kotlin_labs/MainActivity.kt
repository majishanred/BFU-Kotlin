package com.maji.bfu_kotlin_labs

import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val usePhoneNumberTV = findViewById<TextView>(R.id.use_phone_number_tv)
        val useEmailTV = findViewById<TextView>(R.id.use_email_tv)

        val loginInput = findViewById<EditText>(R.id.register_login_input)
        val passwordInput = findViewById<EditText>(R.id.register_password_input)
        val repeatPasswordInput = findViewById<EditText>(R.id.repeat_password_input)

        val registerButton = findViewById<Button>(R.id.register_button)

        usePhoneNumberTV.setOnClickListener {
            loginInput.hint = getString(R.string.enter_phone_number)
            loginInput.inputType = InputType.TYPE_CLASS_PHONE
            usePhoneNumberTV.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
            useEmailTV.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        useEmailTV.setOnClickListener {
            loginInput.hint = getString(R.string.enter_email)
            loginInput.inputType = InputType.TYPE_CLASS_TEXT
            useEmailTV.setTextColor(ContextCompat.getColor(this, R.color.purple_500))
            usePhoneNumberTV.setTextColor(ContextCompat.getColor(this, R.color.black))
        }

        registerButton.setOnClickListener {
            val toast = Toast.makeText(this, "", Toast.LENGTH_LONG)

            var message = ""
            if (loginInput.inputType == InputType.TYPE_CLASS_TEXT && !loginInput.text.toString().contains("@")) {
                message = "Введите корректную электронную почту."
            } else if (loginInput.inputType == InputType.TYPE_CLASS_PHONE && !loginInput.text.toString().contains("+")) {
                message = "Введите корректный номер телефона."
            } else if (passwordInput.text.length < 8) {
                message = "Минимальная длина пароля 8 символов."
            } else if (passwordInput.text.equals(repeatPasswordInput.text)) {
                message = "Введенные пароли разные."
            }

            if (message.isNotEmpty()) {
                toast.setText(message)
                toast.show()
            }
        }
    }
}