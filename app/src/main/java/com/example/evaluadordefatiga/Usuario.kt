package com.example.evaluadordefatiga

import java.io.Serializable

data class Usuario(
    val nombre: String,
    val edad: Int,
    val peso: Double,
    val altura: Double
) : Serializable
