package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entity.Ad
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface PetApi {
    @GET("/api/advertisement/{id}")
    fun getPedById(@Path("id") uuid: String): Single<Ad>
}