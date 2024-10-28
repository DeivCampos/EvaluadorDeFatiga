package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnalysisResultsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analysis_results)

        val resultsTextView = findViewById<TextView>(R.id.textViewResults)

        // Obtener la lista de usuarios desde el UserInfoActivity
        val usuarios = UserInfoActivity.usuarios // Obtener la lista de usuarios global

        // Mostrar los resultados
        if (usuarios.isNotEmpty()) {
            val results = StringBuilder()
            for (usuario in usuarios) {
                results.append("Nombre: ${usuario.nombre}, Edad: ${usuario.edad}, Peso: ${usuario.peso}, Altura: ${usuario.altura}\n")
            }
            resultsTextView.text = results.toString()
        } else {
            resultsTextView.text = "No hay usuarios registrados."
        }

        // Manejar el clic del botón "Ver Usuarios"
        val buttonViewUsers = findViewById<Button>(R.id.buttonViewUsers)
        buttonViewUsers.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent) // No es necesario pasar usuarios aquí, ya que lo tenemos en el UserInfoActivity
        }
    }
}
