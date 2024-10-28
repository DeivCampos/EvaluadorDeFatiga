package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserInfoActivity : AppCompatActivity() {

    // Usar un objeto global para almacenar usuarios, aquí usamos un Companion object
    companion object {
        val usuarios = mutableListOf<Usuario>() // Lista para almacenar los usuarios
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val nameEditText = findViewById<EditText>(R.id.et_name)
        val ageEditText = findViewById<EditText>(R.id.et_age)
        val weightEditText = findViewById<EditText>(R.id.et_weight)
        val heightEditText = findViewById<EditText>(R.id.et_height)
        val saveButton = findViewById<Button>(R.id.btn_save)

        saveButton.setOnClickListener {
            try {
                // Obtener y validar los datos ingresados
                val userName = nameEditText.text.toString()
                val userAge = ageEditText.text.toString().toIntOrNull()
                val userWeight = weightEditText.text.toString().toDoubleOrNull()
                val userHeight = heightEditText.text.toString().toDoubleOrNull()

                // Validación para asegurar que los datos sean correctos
                if (userName.isEmpty() || userAge == null || userWeight == null || userHeight == null) {
                    throw IllegalArgumentException("Todos los campos deben estar completos y ser válidos.")
                }

                // Crear un nuevo usuario y agregarlo a la lista
                val nuevoUsuario = Usuario(userName, userAge, userWeight, userHeight)
                usuarios.add(nuevoUsuario) // Almacenar el nuevo usuario en el objeto global

                // Mostrar mensaje de éxito
                Toast.makeText(this, "Usuario guardado correctamente.", Toast.LENGTH_SHORT).show()

                // Ir a la pantalla de inicio de entrenamiento
                val intent = Intent(this, TrainingStartActivity::class.java)
                startActivity(intent)

            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Ocurrió un error inesperado. Intente nuevamente.", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }
    }
}
