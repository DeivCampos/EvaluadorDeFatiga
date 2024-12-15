package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase




class LoginActivity : AppCompatActivity() {
    private lateinit var correoEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        correoEditText = findViewById(R.id.correoEditTextLogin)
        passwordEditText = findViewById(R.id.passwordEditTextLogin)
        loginButton = findViewById(R.id.loginButton)

        auth = Firebase.auth

        fun logIn(correo: String, password: String) {
            try {
                auth.signInWithEmailAndPassword(correo, password).addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MenuPrincipal::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Correo o Contraseña incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                Log.e("Error", e.message.toString())
            }
        }
        val registrar:Button = findViewById(R.id.crearCuenta)
        registrar.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        // Programar el clic en "¿Olvidaste tu contraseña? Recuperar"

        // Programar el clic en "¿Olvidaste tu contraseña? Recuperar"
        val tvCambiar: TextView = findViewById(R.id.tvCambiar)
        tvCambiar.setOnClickListener {
            val intent = Intent(this, RestaurarContrasena::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            try {
                val correo = correoEditText.text.toString()
                val password = passwordEditText.text.toString()
                if (correo.isEmpty()) {
                    correoEditText.error = "Por favor, ingrese correo."
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    passwordEditText.error = "Por favor, ingrese la contraseña."
                    return@setOnClickListener
                }
                if (password.length < 4) {
                    passwordEditText.error = "La contraseña debe tener al menos 4 caracteres."
                    return@setOnClickListener
                }
                logIn(correo, password)
            } catch (e: Exception) {
                Toast.makeText(this, "Ocurrió un error en el inicio de sesión. Intente de nuevo.", Toast.LENGTH_SHORT).show()
            }
        }
    }








}
