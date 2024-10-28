package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener { login() }
    }

    private fun login() {
        try {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (username.isEmpty()) {
                usernameEditText.error = "Por favor, ingrese el nombre de usuario."
                return
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Por favor, ingrese la contraseña."
                return
            }

            if (password.length < 4) {
                passwordEditText.error = "La contraseña debe tener al menos 4 caracteres."
                return
            }

            if (username == "admin" && password == "1234") {
                val intent = Intent(this, UserInfoActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            Toast.makeText(this, "Ocurrió un error en el inicio de sesión. Intente de nuevo.", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
}
