package br.com.projeto.pets.data.entity

import java.util.Date

data class User(
    val id: Int,
    val active: Boolean,
    val session: Session,
    val address: String,
    val addressNumber: String,
    val city: String,
    val email: String,
    val lastUpdate: Date,
    val name: String,
    val password: String,
    val phone: String,
    val registerAt: Date,
    val state: String,
    val zipCode: String
)

data class NewUser(
    val name: String,
    val email: String,
    val password: String
)

data class Session(
    val expires: Date,
    val token: String
)