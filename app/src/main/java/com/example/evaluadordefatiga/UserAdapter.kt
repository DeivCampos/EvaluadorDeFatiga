package com.example.evaluadordefatiga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val userList: List<Usuario>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val ageTextView: TextView = itemView.findViewById(R.id.textViewAge)
        val weightTextView: TextView = itemView.findViewById(R.id.textViewWeight)
        val heightTextView: TextView = itemView.findViewById(R.id.textViewHeight)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.nameTextView.text = user.nombre
        holder.ageTextView.text = user.edad.toString()
        holder.weightTextView.text = user.peso.toString()
        holder.heightTextView.text = user.altura.toString()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}

