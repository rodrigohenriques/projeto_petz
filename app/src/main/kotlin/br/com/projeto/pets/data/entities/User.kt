package br.com.projeto.pets.data.entities

// Ref: https://github.com/ProjetoPets/api/blob/master/docs/api/user/update.json
data class User(
    val username: String,
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val addressNumber: String,
    val state: String,
    val city: String,
    val zipCode: String,
    val phone: String
)