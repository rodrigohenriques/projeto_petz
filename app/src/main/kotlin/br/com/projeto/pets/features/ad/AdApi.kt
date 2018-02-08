package br.com.projeto.pets.features.ad

import io.reactivex.Single
import retrofit2.http.GET

interface AdApi {
  @GET("/api/advertisement/filter/active")
  fun getAllAd(): Single<Result<Ad>>
}
