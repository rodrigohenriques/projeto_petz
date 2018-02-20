package br.com.projeto.pets.features.ad

data class Ad(
    val id: Int,
    val age: Int,
    val isHatch: Boolean,
    val isVaccinated: Boolean,
    val state: String,
    val city: String,
    val price: Int?,
    val phone: String,
    val registerDate: String,
    val approved: Boolean,
    val breead: Breed,
    val category: Category,
    val user: User,
    val photos: List<Photo>,
    val ageClassification: String?
)

data class Breed(
    val id: Int,
    val name: String
)

data class Category(
    val id: Int,
    val name: String
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val password: String,
    val address: String,
    val addressNumber: String,
    val state: String,
    val city: String,
    val zipCode: String,
    val phone: String,
    val registerAt: String,
    val lastUpdate: String?,
    val active: Boolean
)

data class Photo(
    val id: Int,
    val photo: String,
    val registerDate: String
)
