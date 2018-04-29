package br.com.projeto.pets.features.create

import retrofit2.http.POST


interface CreateApi {

    @POST("")
    fun createAd()

}