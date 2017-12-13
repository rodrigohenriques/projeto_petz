package br.com.projeto.pets.data.entities

// Ref: https://github.com/ProjetoPets/api/blob/master/docs/api/user/create.json
data class CreateUser(
    val name: String,
    val email: String,
    val password: String
)
