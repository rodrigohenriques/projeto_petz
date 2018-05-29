package br.com.projeto.pets.features.create

import io.reactivex.Single
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.POST


interface CreateApi {
    @POST("/api/advertisement/")
    fun createAd(@Body adCreate:AdCreateModel) : Single<ResponseBody>
}