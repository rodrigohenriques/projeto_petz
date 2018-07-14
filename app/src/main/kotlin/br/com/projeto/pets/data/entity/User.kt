package br.com.projeto.pets.data.entity

import java.util.Date

data class User(
        val id: Int,
        val active: Boolean = true,
        val session: Session? = null,
        val address: String? = null,
        val addressNumber: String? = null,
        val city: String?,
        val email: String?,
        val lastUpdate: Date? = null,
        val name: String?,
        val password: String? = null,
        val phone: String? = null,
        val registerAt: Date? = null,
        val state: String? = null,
        val zipCode: String? = null
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