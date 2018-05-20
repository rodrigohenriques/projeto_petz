package br.com.projeto.pets.features.create

import br.com.projeto.pets.features.ad.AgeClassification
import br.com.projeto.pets.features.ad.Photo


data class AdCreateModel(
        val age: Int,
        val ageClassification: AgeClassification,
        val isHatch: Boolean, // ninhada
        val isVaccinated: Boolean,
        val state:String,
        val city:String,
        val price:String,
        val phone:String,
        val approved: Boolean,
        val breedId: Int,
        val categoryId: Int,
        val userId: Int,
        val description: String,
        val photos: List<Photo>
)