package com.example.evaluadordefatiga

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UserListActivity : AppCompatActivity() {

    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers)
        recyclerViewUsers.layoutManager = LinearLayoutManager(this)

        // Obtener la lista de usuarios desde el UserInfoActivity
        val usuarios = UserInfoActivity.usuarios // Obtener la lista de usuarios global

        // Configurar el adaptador con la lista de usuarios
        userAdapter = UserAdapter(usuarios) // Asegúrate de que tu adaptador esté configurado para aceptar una lista de usuarios
        recyclerViewUsers.adapter = userAdapter

        val buttonVolver = findViewById<Button>(R.id.buttonVolver)
        buttonVolver.setOnClickListener {
            val intent = Intent(this, AnalysisResultsActivity::class.java)
            startActivity(intent)
        }
    }
}
