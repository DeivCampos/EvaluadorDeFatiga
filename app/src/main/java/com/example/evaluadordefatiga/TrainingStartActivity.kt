package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TrainingStartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_start)

        // Obtener los datos pasados desde UserInfoActivity
        val userName = intent.getStringExtra("USER_NAME")
        val userAge = intent.getStringExtra("USER_AGE")
        val userWeight = intent.getStringExtra("USER_WEIGHT")
        val userHeight = intent.getStringExtra("USER_HEIGHT")

        // Referencia al TextView para mostrar la información del usuario
        val summaryTextView = findViewById<TextView>(R.id.tv_user_info)

        // Mostrar los datos en el TextView
        summaryTextView.text = "Nombre: $userName\nEdad: $userAge\nPeso: $userWeight kg\nEstatura: $userHeight cm"
        // Configurar el botón para iniciar el entrenamiento
        val startTrainingButton = findViewById<Button>(R.id.btn_start_training)
        startTrainingButton.setOnClickListener {
            // Iniciar la actividad de splash
            val intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
    }
}