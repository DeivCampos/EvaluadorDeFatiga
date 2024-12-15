package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluadordefatiga.Models.Usuario
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class UserInfoActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        auth = Firebase.auth
        val nombreUsuario = findViewById<TextView>(R.id.NombreUsuario)

        val database = FirebaseDatabase.getInstance().getReference("Usuario")
        database.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val nombre = data.child("nombre").getValue(String::class.java)
                    val correo = data.child("correo").getValue(String::class.java)
                    if (correo == auth.currentUser?.email) {
                        nombreUsuario.text = nombre
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@UserInfoActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
            }})

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
    }



}
