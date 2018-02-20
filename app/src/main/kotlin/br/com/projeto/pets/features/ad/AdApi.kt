package br.com.projeto.pets.features.ad

import io.reactivex.Single
import retrofit2.http.GET

interface AdApi {
  @GET("/api/breed")
  fun getAllAd(): Single<Result<Ad>>
}
