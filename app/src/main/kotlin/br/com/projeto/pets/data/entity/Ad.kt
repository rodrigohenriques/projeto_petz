package br.com.projeto.pets.data.entity

data class Ad(
        val id: Int,
        val age: Int,
        val isHatch: Boolean,
        val isVaccinated: Boolean,
        val state: String,
        val city: String,
        val price: Double?,
        val phone: String,
        val registerDate: String,
        val approved: Boolean,
        val breed: Breed,
        val category: Category,
        val user: User?,
        val photos: List<Photo>,
        val ageClassification: AgeClassification?
)

data class Breed(
    val id: Int,
    val name: String
)

data class Category(
    val id: Int,
    val name: String
)

data class Photo(
    val id: Int,
    val photo: String,
    val registerDate: String
)

data class AgeClassification(
    val id: Int,
    val name: String
)