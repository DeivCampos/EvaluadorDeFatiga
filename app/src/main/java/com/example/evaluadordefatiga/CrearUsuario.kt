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
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class CrearUsuario : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_crear_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val crearUsuario: Button = findViewById(R.id.crearUsuario1)
        val volverMenu:Button = findViewById(R.id.VolverMenu)
        volverMenu.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }

        val bottomnav = findViewById<BottomNavigationView>(R.id.bottomnav)
        bottomnav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.item_1 -> {
                    finish()
                    val intent = Intent(this, MenuPrincipal::class.java)
                    startActivity(intent)
                    true
                }
                R.id.item_2 -> {
                    finish()
                    val intent = Intent(this, UserInfoActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }


        val nombreET = findViewById<EditText>(R.id.nombreCrear)
        val edadET = findViewById<EditText>(R.id.edadCrear)
        val pesoET = findViewById<EditText>(R.id.pesoCrear)
        val alturaET = findViewById<EditText>(R.id.alturaCrear)

        try {
            crearUsuario.setOnClickListener {

                val nombre: String = nombreET.text.toString()
                val edad: Int = edadET.text.toString().toInt()
                val peso: Double = pesoET.text.toString().toDouble()
                val altura: Double = alturaET.text.toString().toDouble()

                if (nombre.isEmpty()) {
                    nombreET.error = "Ingrese un nombre..."
                    return@setOnClickListener
                }
                if (edad.toString().isEmpty()) {
                    edadET.error = "Ingrese una edad..."
                    return@setOnClickListener
                }
                if (peso.toString().isEmpty()) {
                    pesoET.error = "Ingrese un peso..."
                    return@setOnClickListener
                }
                if (altura.toString().isEmpty()) {
                    alturaET.error = "Ingrese una altura..."
                    return@setOnClickListener
                }

                val database = FirebaseDatabase.getInstance().getReference("Usuario")

                val id = database.child("Usuario").push().key
                val usuario = Usuario(id, nombre, null, edad, peso, altura)
                database.child(id!!).setValue(usuario)
                nombreET.setText("")
                edadET.setText("")
                pesoET.setText("")
                alturaET.setText("")
                Toast.makeText(this, "Usuario Creado Exitosamente", Toast.LENGTH_SHORT).show()

            }
        } catch (e:Exception){
            Log.e("Error", e.message.toString())
        }



    }
}