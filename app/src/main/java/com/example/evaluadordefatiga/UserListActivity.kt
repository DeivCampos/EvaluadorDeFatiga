package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase

class UserListActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)


        retrieveAndDisplayUsuarios()

        val buttonVolver = findViewById<Button>(R.id.buttonVolver)
        buttonVolver.setOnClickListener {
            val intent = Intent(this, AnalysisResultsActivity::class.java)
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
    }
    private fun retrieveAndDisplayUsuarios() {
        val databaseReference = FirebaseDatabase.getInstance().getReference("Usuario")
        val linearLayout = findViewById<LinearLayout>(R.id.LLayout) // Make sure your layout has this ID

        databaseReference.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val snapshot = task.result
                if (snapshot.exists()) {
                    for (userSnapshot in snapshot.children) {
                        val nombre = userSnapshot.child("nombre").getValue(String::class.java) // Extract 'nombre'
                        if (!nombre.isNullOrEmpty()) {
                            val textView = TextView(this)
                            textView.text = "Nombre: $nombre"
                            textView.textSize = 14f
                            textView.setPadding(16, 16, 16, 16)

                            // Add the TextView to the LinearLayout
                            linearLayout.addView(textView)
                        }
                    }
                } else {
                    Toast.makeText(this, "No data found in the database", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Failed to retrieve data: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
