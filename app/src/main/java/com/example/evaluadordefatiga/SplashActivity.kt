package com.example.evaluadordefatiga

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Usar un Handler para retrasar el inicio de la siguiente actividad
        Handler(Looper.getMainLooper()).postDelayed({
            // Iniciar la actividad de monitoreo
            val intent = Intent(this, MonitoringActivity::class.java)
            startActivity(intent)
            // Cerrar la actividad de splash para que no se pueda volver a ella
            finish()
        }, 5000) // 5000 milisegundos = 5 segundos
    }
}
