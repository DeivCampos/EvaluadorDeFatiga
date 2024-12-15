package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MenuPrincipal : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_principal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        try {

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

            val crearUsuario: Button = findViewById(R.id.crearUsuario)
            crearUsuario.setOnClickListener {
                val intent = Intent(this, CrearUsuario::class.java)
                startActivity(intent)
            }

            val verUsuarios: Button = findViewById(R.id.verUsuarios)
            verUsuarios.setOnClickListener {
                val intent = Intent(this, AnalysisResultsActivity::class.java)
                startActivity(intent)
            }


            val botonLogout: Button = findViewById(R.id.logout)
            botonLogout.setOnClickListener {
                Firebase.auth.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        } catch (e:Exception) {
            Log.e("Error", e.message.toString())
        }



    }
}