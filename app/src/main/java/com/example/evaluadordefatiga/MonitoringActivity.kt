package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MonitoringActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_monitoring)

        // Configurar el botón "Finalizar Entrenamiento"
        val finishTrainingButton = findViewById<Button>(R.id.buttonFinishTraining)
        finishTrainingButton.setOnClickListener {
            // Iniciar la actividad de análisis de resultados
            val intent = Intent(this, AnalysisResultsActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad actual si deseas que el usuario no regrese a esta
        }
    }
}
