package br.com.projeto.pets.data.api

import br.com.projeto.pets.data.entity.Ad
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AdApi {
    @GET("/api/advertisement?active=true")
    fun getAllAd(@Query("breedId") breedId: Int? = null,
                 @Query("ageClassificationId") ageClassificationId: Int? = null,
                 @Query("city") city: String? = null,
                 @Query("categoryId") categoryId: Int?): Single<List<Ad>>
}
