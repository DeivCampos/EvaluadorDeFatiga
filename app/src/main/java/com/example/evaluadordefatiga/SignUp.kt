package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.evaluadordefatiga.Models.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignUp : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //conexion con base de datos y creacion de datos de usuario al registrar
        auth = Firebase.auth

        val database = FirebaseDatabase.getInstance().getReference("Usuario")

        fun signUp(correo: String, password: String, nombreApellido: String, edad: Int, peso: Double, altura: Double) {
            auth.createUserWithEmailAndPassword(correo, password).
            addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val id = database.child("Usuario").push().key
                    val usuario = Usuario(id, nombreApellido, correo, edad, peso, altura)
                    database.child(id!!).setValue(usuario)
                    val intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext,"Correo/Clave Incorrectas", Toast.LENGTH_SHORT).show()
                }
            }
        }

        try {

            //obtener datos, revisar si son validos e intentar registar cuenta
            val boton: Button = findViewById(R.id.signUpButton)
            boton.setOnClickListener {
                val nombreApellido: String = findViewById<EditText>(R.id.nombreApellidoEditText).text.toString()
                val correo: String = findViewById<EditText>(R.id.correoEditText).text.toString()
                val edad: Int = findViewById<EditText>(R.id.edadEditText).text.toString().toInt()
                val peso: Double = findViewById<EditText>(R.id.pesoEditText).text.toString().toDouble()
                val altura: Double = findViewById<EditText>(R.id.alturaEditText).text.toString().toDouble()
                val password: String = findViewById<EditText>(R.id.passwordEditText).text.toString()

                if (nombreApellido.isEmpty()) {
                    Toast.makeText(this, "Ingrese Nombre y apellido...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (correo.isEmpty()) {
                    Toast.makeText(this, "Ingrese correo...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (edad.toString().isEmpty()) {
                    Toast.makeText(this, "Ingrese edad...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (peso.toString().isEmpty()) {
                    Toast.makeText(this, "Ingrese peso...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (altura.toString().isEmpty()) {
                    Toast.makeText(this, "Ingrese altura...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                if (password.isEmpty() || password.length < 4) {
                    Toast.makeText(this, "Ingrese contraseña válida...", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                signUp(correo, password, nombreApellido, edad, peso, altura)
            }

        } catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }


    }
}