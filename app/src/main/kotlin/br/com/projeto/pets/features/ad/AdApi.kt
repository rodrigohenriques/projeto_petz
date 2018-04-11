package br.com.projeto.pets.features.ad

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AdApi {
    @GET("/api/advertisement/filter/active")
    fun getAllAd(@Query("breedId") breedId: Int? = null,
                 @Query("ageClassificationId") ageClassificationId: Int? = null): Single<List<Ad>>
}
