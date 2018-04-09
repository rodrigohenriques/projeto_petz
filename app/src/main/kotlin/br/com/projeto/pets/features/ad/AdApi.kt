package br.com.projeto.pets.features.ad

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AdApi {
    @GET("/api/advertisement/filter/active")
    fun getAllAd(@Query("breedId") breedId: String? = null,
                 @Query("ageClassificationId") ageClassificationId: String? = null): Single<List<Ad>>
}
