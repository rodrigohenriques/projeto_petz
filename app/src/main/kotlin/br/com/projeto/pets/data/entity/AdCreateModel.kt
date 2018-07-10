package br.com.projeto.pets.data.entity


data class AdCreateModel(
        val age: Int,
        val ageClassificationId: Int,
        val isHatch: Boolean,
        val isVaccinated: Boolean,
        val state: String,
        val city: String,
        val price: String,
        val phone: String,
        val approved: Boolean,
        val breedId: Int,
        val categoryId: Int,
        val userId: Int,
        val description: String,
        val photos: List<Photo>
)