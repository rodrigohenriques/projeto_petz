package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entity.AdCreateModel
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface CreateApi {
    @POST("/api/advertisement/")
    fun createAd(@Body adCreate: AdCreateModel) : Single<ResponseBody>
}