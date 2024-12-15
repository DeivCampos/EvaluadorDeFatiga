package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AnalysisResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_results)

        val resultsTextView = findViewById<TextView>(R.id.textViewResults)


        // Manejar el clic del botón "Ver Usuarios"
        val buttonViewUsers = findViewById<Button>(R.id.buttonViewUsers)
        buttonViewUsers.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent) // No es necesario pasar usuarios aquí, ya que lo tenemos en el UserInfoActivity
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
}
